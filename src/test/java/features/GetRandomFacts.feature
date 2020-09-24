Feature: Get random facts with specified amount

  Scenario: This scenario will get random facts
    Given I am on endpoint "/facts/random"
    When I send "GET" request with these parameters
      | animal_type | amount |
      | dog         | 4      |
    Then the status code should be 200
    And the response should have "used" with "false"