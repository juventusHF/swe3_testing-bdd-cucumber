Feature: Testing employee API

  Scenario: Retrieve a existing user
    Given an employee with the id 1
    When the employee is retrieved from the service
    Then an employee should be returned