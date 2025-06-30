Feature: Register a new pet in the system

  As an API user
  I want to register a new pet
  So that the API successfully creates new pets

  Scenario Outline: Successfully register a pet
    Given the API is available
    When I send a POST request with the data
      | consume   | typeCase   | name   | categoryName   | photoUrls   | status   |
      | <consume> | <typeCase> | <name> | <categoryName> | <photoUrls> | <status> |
    Then the service response is validated according to the case

    Examples:
      | consume      | typeCase | categoryName | name     | photoUrls                     | status    |
      #Successful cases
      | NEW_PET_BODY | pass     | cat          | Tony     | http://catIamge.com/image.jpg | available |
      | NEW_PET_BODY | pass     | dog          | Patricio | http://dogIamge.com/image.jpg | sold      |
      | NEW_PET_BODY | pass     | cat          | Lucas    | http://catIamge.com/image.jpg | pending   |
      #Name field error - required
      | NEW_PET_BODY | error    | cat          | [void]   | http://dogIamge.com/image.jpg | available |
      #PhotoUrls field photoUrls - required
      | NEW_PET_BODY | error    | cat          | Tony     | [void]                        | pending   |
      #Status field error - Domain value error
      | NEW_PET_BODY | error    | cat          | Tony     | http://dogIamge.com/image.jpg | new       |
      #Name field error - Format error
      | NEW_PET_BODY | error    | cat          | -$%-%    | http://dogIamge.com/image.jpg | sold      |