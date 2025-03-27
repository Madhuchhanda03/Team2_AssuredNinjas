@UserAll
Feature: Create User Role Status API

  @User
  Scenario: Verify user role status update
    Given User has a valid Bearer token
    When User sends a POST request with user data
    Then The response status code should be 201

  @User2
  Scenario: Validate LMS v2Users API response
    When User sends a GET request to endpoint
    Then Response status is 200

  @GetAll
  Scenario: Retrieve all users with valid authorization
    When User sends a GET request to endpoint all users
    Then API should respond with status 200

  @Getrole
  Scenario: Fetch all user roles successfully
    Given the API endpoint is set to get user roles
    When a GET request is sent to fetch user roles
    Then the response status code should be 200

  @GetActiveUsers
  Scenario: Fetch all active users successfully
    When a GET request is sent to fetch active users
    Then the response status code is 200

  @GetRoles
  Scenario: Fetch all roles successfully
    Given the API endpoint is set to get all roles
    When a GET request is sent to fetch all roles
    Then the response status code 200

  @Getemail
  Scenario: Fetch all user emails successfully
    Given the API endpoint is set to fetch user emails
    When a GET request is sent to fetch user emails
    Then the response status contain 200

  @Getuserid
  Scenario Outline: Validate valid user details from "User" sheet
    Given I have the LMS API endpoint to get user details
    When User sends a GET request with user data
    Then I should get a valid response with status code 200

  @GetUserDetails
  Scenario: User sends a GET request to fetch user data
    Given API endpoint to get user details
    When User sends a GET request with user
    Then The API response should contain the user details

  @GetRoleDetails
  Scenario: User sends a GET request to fetch role data
    Given User creates GET Request for the LMS API User Role endpoint
    When User sends a GET request to endpoint role
    Then The API response should contain the role details

  @GetProgramDetails
  Scenario: User sends a GET request to fetch program data
    Given User creates a GET Request for the LMS API Program endpoint
    When User sends a GET request endpoint program data
    Then The API response should contain the program details

  @GetProgramBatch
  Scenario: User sends a GET request to fetch program batch data
    Given User creates a GET Request for the LMS API Program Batch endpoint
    When User sends a GET request to the Program Batch endpoint
    Then The API response should contain the program batch details

  @Getuserdetails
  Scenario: Fetching user details by user ID
    Given the API endpoint to fetch user details is available
    When I send a GET request to fetch user details
    Then The API response should contain the user details

  @GetUsersbyStatus
  Scenario: User sends a GET request to fetch all users by status
    Given User creates a GET Request for the LMS API Users by Status endpoint
    When User sends a GET request to the Users by Status endpoint with id string
    Then The API response should contain users data for all statuses

  @putuser
  Scenario: Update an existing user with valid details
    Given User creates PUT Request for the LMS API endpoint
    When Send a PUT request with valid data
    Then the response should contain the updated user details

  @putinvalid
  Scenario: Update an existing user with invalid details
    Given User creates PUT Request for the endpoint
    When Send a PUT request with invalid data
    Then the response should see 400

  @UpdateUserLoginStatus
  Scenario: Update login status of a user successfully
    Given I have the API endpoint to update user login with ID
    When I send a PUT request to update the user login status
    Then the response should contain the updated user login status

  @UpdateRoleProgramBatch
  Scenario: Update role, program, and batch status successfully
    Given I have the API endpoint to update role, program, and batch status for user ID
    When I send a PUT request to update the role, program, and batch status
    Then the response should contain the updated role, program, and batch status

  @UpdateUserRole
  Scenario: Update role and status for a user successfully
    Given I have the API endpoint to update the role for user iD
    When I send a PUT request to update the user role
    Then the response should contain the updated role and status

  @DeleteUser
  Scenario: Delete user successfully
    Given I have the API endpoint to delete user with id
    When I send a DELETE request to remove the user
    Then the status code should be 200
