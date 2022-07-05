Feature: Create Project

  Scenario: Create project with name
    When the user creates the following request with the following headers
    And the user sends a POST request to "https://www.pivotaltracker.com/services/v5/projects"
    Then the user verifies that the response status code is 200
    And the user verifies that the response matches the JSON Schema "POSTProjectResponseSchema.json"
