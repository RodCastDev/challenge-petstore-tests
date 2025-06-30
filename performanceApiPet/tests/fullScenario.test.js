import http from 'k6/http';
import { check, group, sleep, fail } from 'k6';

export const options = {
  thresholds: {
    http_req_duration: ['p(95)<600'], 
    http_req_failed: ['rate<0.01'],
    checks: ['rate>0.99'],
  },
  scenarios: {
    fullFlow: {
      executor: 'constant-vus',
      vus: __ENV.VUS ? parseInt(__ENV.VUS) : 15,
      duration: __ENV.DURATION || '1m',
    },
  },
};

export default function () {
  const baseUrl = __ENV.BASE_URL || 'http://localhost:8080/api';
  const petId = Math.floor(Math.random() * 100000);
  const petName = `K6_Pet_${petId}`;
  const headers = { headers: { 'Content-Type': 'application/json' }, tags: { scenario: 'create_get_delete' } };

  group('Full CRUD flow for pet entity', () => {
    // Create pet
    const payload = JSON.stringify({ id: petId, name: petName, status: 'available' });
    let res = http.post(`${baseUrl}/pet`, payload, headers);
    let ok = check(res, { 'Create OK': (r) => r.status === 200 });
    if (!ok) fail(`Pet creation failed for ID: ${petId}`);
    sleep(1);

    // Get pet
    res = http.get(`${baseUrl}/pet/${petId}`);
    ok = check(res, {
      'Get OK': (r) => r.status === 200,
      'Correct name returned': (r) => r.json().name === petName,
    });
    if (!ok) fail(`Pet retrieval failed for ID: ${petId}`);
    sleep(1);

    // Delete pet
    res = http.del(`${baseUrl}/pet/${petId}`);
    ok = check(res, { 'Delete OK': (r) => r.status === 200 });
    if (!ok) fail(`Pet deletion failed for ID: ${petId}`);
  });

  sleep(1);
}
