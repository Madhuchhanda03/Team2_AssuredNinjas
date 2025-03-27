@Login
Feature: User Login with Bearer Token

  Scenario Outline: Login with valid and invalid credentials
    Given User provides "<scenario>" credentials
    When User sends a POST request to endpoint
    Then API should respond with expected status <status>

    Examples:
      | scenario      | status | message     |
      | validLogin    | 200    | token           |
      | invalidLogin  | 401    | Invalid username or password |
