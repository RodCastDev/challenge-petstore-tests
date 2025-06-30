Feature: Retrieve pet information by ID

  As an API user
  I want to retrieve a pet's information using its ID
  So that I can confirm whether it exists and is stored correctly

  Scenario Outline: Validate pet retrieval using dynamic or manual IDs
    Given the API is available
    And I prepare the ID "<idOrigin>" with value "<idValue>"
    When I consume the endpoint to retrieve a pet with the provided ID
    Then the response status should be <expectedStatus> and the "<name>"

    Examples:
      | idOrigin | idValue | expectedStatus | name     |
      | valid    | -       | 200            | Tony     |
      | valid    | -       | 200            | Patricio |
      | valid    | -       | 200            | Lucas    |
      | manual   | 99999   | 404            | Mateo    |
      | manual   | 0       | 404            | Lily     |
      | manual   | -123    | 404            | Estrella |
