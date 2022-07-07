Feature: Login

  Scenario: Login with invalid credentials
    Given the user goes to login page
    When the user enters "marcos.x86@outlook.com" in the username field
    And the user enters "123213213" in the password field
    Then verfies that the following error message is displayed
    """
    Invalid username/password
    """
