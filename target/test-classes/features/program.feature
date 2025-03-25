
@tag
Feature: Program Feature
Background:
When Admin sets Authorization

  @tag1
  Scenario: Check if Admin able to create a program with valid endpoint and request body with Authorization
    Given Admin creates POST Request for the LMS with request body
    When Admin sends "POST" HTTPS Request and  request Body with "addNewProgram" endpoint
    Then Admin receives 201 Status code
    
  @tag2  
 Scenario: Check if Admin able to create a program with invalid endpoint
   Given Admin creates POST Request for the LMS with request body
   When Admin sends "POST" HTTPS Request and  request Body with invalid endpoint
   Then Admin receives 404 Status code
  
   
 @tag3
  Scenario: Check if Admin able to create a program with already existing program name
   Given Admin creates POST Request for the LMS with request body
   When Admin sends "POST" HTTPS Request and  request Body with "addNewProgram" endpoint
  Then Admin receives 400 Status code
  
  
 @tag4 
 Scenario:  Check if Admin able to create a program with invalid method
  Given  Admin creates POST Request for the LMS with request body
  When  Admin sends "GET" HTTPS Request and  request Body with "addNewProgram" endpoint
  Then  Admin receives 405 Status code
  
  
 @tag5
 Scenario: Check if Admin able to create a program with invalid request body
   Given Admin creates POST Request for the LMS with invalid request body
   When Admin sends "POST" HTTPS Request and  request Body with "addNewProgram" endpoint
   Then Admin receives 400 Status code
  
 @tag6
  Scenario: Check if Admin able to create a program with missing values in the request body
   Given Admin creates POST Request for the LMS with missing values in the request body
   When Admin sends "POST" HTTPS Request and  request Body with "addNewProgram" endpoint
   Then Admin receives 400 Status code
  
  
 @tag7
  Scenario: Check if Admin able to create a program with missing additional field
   Given Admin creates POST Request for the LMS with request body with missing additional field
   When Admin sends "POST" HTTPS Request and  request Body with "addNewProgram" endpoint
   Then Admin receives 201 Status code
   
   ############################ GET REQUEST ALL PROGRAM #############################################
   
    @tag8
  Scenario:  Check if Admin able to retrieve all programs with valid Endpoint
    Given Admin creates GET Request for the LMS API
    When Admin sends "GET" HTTPS Request with "getAllPrograms" endpoint
    Then Admin receives 200 Status code  
    
   @tag9
  Scenario:  Check if Admin able to retrieve all programs with invalid Endpoint
    Given Admin creates GET Request for the LMS API
    When  Admin sends "GET" HTTPS Request with invalid endpoint
    Then  Admin receives 404 Status code 
  
   @tag10
  Scenario:  Check if Admin able to retrieve all programs with invalid Method
    Given Admin creates GET Request for the LMS API
    When  Admin sends "PUT" HTTPS Request with "getAllPrograms" endpoint
    Then  Admin receives 405 Status code  
  
   @tag11
  Scenario:  Check if Admin able to retrieve all programs without Authorization
    Given  Admin creates GET Request for the LMS API without Authorization
    When   Admin sends "GET" HTTPS Request with "getAllPrograms" endpoint
    Then   Admin receives 401 Status code    
  
  
  
  