# Performance Test Strategy

## Objective
To evaluate the performance of critical endpoints in the Swagger Petstore API under different load conditions, simulating real-world usage scenarios.

## Selected Scenarios
We chose the following scenarios based on expected high usage and business relevance:

- **GET /pet/{id}**: Simulates pet lookup by ID, a frequent user action.
- **POST /pet**: Represents the creation of new pets in the system.
- **Full Scenario**: Combines GET and POST to simulate an end-to-end flow under load.

## Load Conditions
All tests were executed with the following parameters:
- **Virtual Users (VUs)**: 15 to 20
- **Rate**: 10 requests per second
- **Duration**: 20 seconds per test

## Coverage Justification
We focused on the most relevant read/write operations in the API. Less frequently used operations like PUT and DELETE were excluded for this round due to time constraints and lower business impact. These can be evaluated in future testing cycles.

