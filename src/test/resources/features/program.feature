
@tag
Feature: Program Feature
Background:
When Admin sets Authorization

  @tag1
  Scenario: Check if Admin able to create a program with valid endpoint and request body with Authorization
    Given Admin creates POST Request for the LMS with request body
    When Admin sends "POST" HTTPS Request and  request Body with "addNewProgram" endpoint
    Then Admin receives 201 Created Status with response body.
    
  @tag2  
 Scenario: Check if Admin able to create a program with invalid endpoint
   Given Admin creates POST Request for the LMS with request body
   When Admin sends "POST" HTTPS Request and  request Body with invalid endpoint
   Then Admin receives 404 not found  Status with message and boolean success details
  
   
 @tag3
  Scenario: Check if Admin able to create a program with already existing program name
   Given Admin creates POST Request for the LMS with request body
   When Admin sends "POST" HTTPS Request and  request Body with "addNewProgram" endpoint
  Then Admin receives 400 Bad Request Status with message and boolean success details
  
 @tag4 
 Scenario: Check if Admin able to create a program with invalid request body
   Given Admin creates POST Request for the LMS with invalid request body
   When Admin sends "POST" HTTPS Request and  request Body with "addNewProgram" endpoint
   Then Admin receives 400 Bad Request Status
  
 @tag5
  Scenario: Check if Admin able to create a program with missing values in the request body
   Given Admin creates POST Request for the LMS with missing values in the request body
   When Admin sends "POST" HTTPS Request and  request Body with "addNewProgram" endpoint
   Then Admin receives 400 Bad Request Status
  
  
 @tag6
  Scenario: Check if Admin able to create a program with missing additional field
   Given Admin creates POST Request for the LMS with request body with missing additional field
   When Admin sends "POST" HTTPS Request and  request Body with "addNewProgram" endpoint
   Then Admin receives 201 Created Status with response body.
   
   
  