Feature: Delete pet using session ID

  As an API user
  I want to delete a pet using the ID stored in session
  So that the system removes the previously created pet

  @resetIds
  Scenario: Reset the list of valid pet IDs
    Given the API is available

  Scenario Outline: Delete a pet with valid or manual ID
    Given the API is available
    And I prepare the DELETE ID "<idOrigin>" with value "<idValue>"
    When I consume the endpoint to delete the pet with the provided ID
    Then the response status should be <expectedStatus>

    Examples:
      | idOrigin | idValue | expectedStatus |
      | valid    | -       | 200            |
      | valid    | -       | 200            |
      | valid    | -       | 200            |
      | manual   | 89      | 404            |
      | manual   | 400     | 404            |


