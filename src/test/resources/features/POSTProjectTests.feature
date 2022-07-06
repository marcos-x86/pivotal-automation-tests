Feature: Create Project

  @deleteProjectPostCondition
  Scenario: Create project with name
    When the user sends a POST request to "https://www.pivotaltracker.com/services/v5/projects" with the body
    """
    {
      "name": "Project Automation"
    }
    """
    Then the user verifies that the response status code is 200
    And the user verifies that the response matches the JSON Schema "POSTProjectResponseSchema.json"
    And the user verifies that the response contains the following data
      | name | Project Automation |
      | kind | project            |
