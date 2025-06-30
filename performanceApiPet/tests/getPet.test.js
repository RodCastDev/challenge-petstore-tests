import http from 'k6/http';
import { check, group, sleep, fail } from 'k6';

export const options = {
  thresholds: {
    http_req_duration: ['p(95)<400'],
    http_req_failed: ['rate<0.01'],
    checks: ['rate>0.99'],
  },
  scenarios: {
    get_pet: {
      executor: 'constant-arrival-rate',
      rate: __ENV.RATE ? parseInt(__ENV.RATE) : 10,
      timeUnit: '1s',
      duration: __ENV.DURATION || '20s',
      preAllocatedVUs: 20,
    },
  },
};

const PET_ID = __ENV.PET_ID || 1;

export default function () {
  group(`Pet lookup by ID ${PET_ID}`, () => {
    const url = `https://petstore.swagger.io/v2/pet/${PET_ID}`;
    const res = http.get(url, { tags: { scenario: 'get_pet' } });

    const ok = check(res, {
      'Status is 200': (r) => r.status === 200,
      'Field "name" exists': (r) => r.json().hasOwnProperty('name'),
    });

    if (!ok) {
      fail(`Request failed for pet ID: ${PET_ID}`);
    }
  });

  sleep(1); 
}