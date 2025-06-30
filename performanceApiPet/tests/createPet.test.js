import http from 'k6/http';
import { check, group, sleep, fail } from 'k6';

export const options = {
  thresholds: {
    // Response Time
    http_req_duration: ['p(95)<500'], 
    // Request Fails
    http_req_failed: ['rate<0.01'],   
    // Funcional Checks
    checks: ['rate>0.99'],         
  },
  scenarios: {
    constant_load: {
      executor: 'constant-vus',
      vus: __ENV.VUS ? parseInt(__ENV.VUS) : 15,
      duration: __ENV.DURATION || '1m',
    },
  },
};

function generatePetData() {
  const uniqueId = Math.floor(Math.random() * 1000000);
  return {
    id: uniqueId,
    name: `Pet_${Math.random().toString(36).substring(2, 8)}`,
    status: 'available',
  };
}

export default function () {
  group('Crear una nueva mascota', () => {
    const pet = generatePetData();

    const payload = JSON.stringify(pet);
    const headers = {
      headers: { 'Content-Type': 'application/json' },
      tags: { test_type: 'create_pet' },
    };

    const res = http.post(`${__ENV.BASE_URL || 'http://localhost:8080'}/api/pet`, payload, headers);

    const checkResult = check(res, {
      'Status 200': (r) => r.status === 200,
      'Tiempo < 500ms': (r) => r.timings.duration < 500,
      'Contiene ID': (r) => r.body.includes(pet.id.toString()),
    });

    if (!checkResult) {
      console.error(`Error en la creación de la mascota: ${res.status}`);
      fail('La validación falló');
    }
  });

  sleep(1);
}