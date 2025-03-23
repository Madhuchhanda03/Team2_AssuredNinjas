
@tag
Feature: Program Feature
Background:
When Admin sets Authorization
  @tag1
  Scenario: Check if Admin able to create a program with valid endpoint and request body with Authorization
    Given Admin creates POST Request for the LMS with request body
    When Admin sends HTTPS Request and  request Body with endpoin
    Then Admin receives 201 Created Status with response body.


