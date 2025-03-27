@tag
Feature: Batch Feature
  Background:
    When Admin sets Authorization


#existing batch info

  Scenario: Check if admin able to create a Batch with valid endpoint and request body (existing value in Batch Name)
    Given Admin creates POST Request  with existing value in request body
    When Admin sends HTTPS POST Request with endpoint to create batch
    Then Admin receives 400 Bad Request Status with message in the response body

#with missing mandatory fields

  Scenario: Check if admin able to create a Batch missing mandatory fields in request body
    Given Admin creates POST Request  with invalid data in request body
    When Admin sends POST Request with endpoint to create batch
    Then Admin receives 400 Bad Request Status with message

#with invalid endpoint

  Scenario: Check if admin able to create a batch with invalid endpoint
    Given Admin creates POST Request
    When Admin sends HTTPS Request with invalid endpoint
    Then Admin receives 404 not found Status

#missing additional fields

  Scenario: Check if admin able to create a batch with missing additional fields
    Given Admin creates POST Request with missing additional fields
    When Admin sends HTTPS Request with valid endpoint to create batch
    Then Admin receives 201 Created Status with response

#invalid data

  Scenario: Check if admin able to create a batch with invalid data in request body
    Given Admin creates POST Request with invalid data fields in request body
    When Admin sends HTTPS POST Request with valid endpoint
    Then Admin receives 400 Bad Request Status with message and boolean success

  #get all batches

  Scenario: Check if admin able to retrieve all batches  with valid LMS API
    Given Admin creates GET Request
    When Admin sends HTTPS Get Request with endpoint to get batch
    Then Admin receives 200 OK Status with response body

#get all invalid endpoint

  Scenario: Check if admin able to retrieve all batches with invalid Endpoint
    Given Admin creates GET Request with invalid endpoint
    When Admin sends HTTPS GET Request with invalid endpoint to get all batches
    Then Admin receives 404 status with error message Not Found

  Scenario: Check if admin gets 401 when retrieving all batches without Authorization
    Given Admin creates GET Request without authorization
    When Admin sends HTTPS Get Request to get batch without authorization
    Then Admin receives 401 Unauthorized Status with error message


  #get batch by id

  Scenario: Check if admin able to retrieve a batch with valid BATCH ID
    Given Admin creates GET Request with valid Batch ID
    When Admin sends Get Request with endpoint for batch module
    Then Admin receives 200 OK Status with response in the response body

  #get batch by name

  Scenario: Check if admin able to retrieve a batch with valid BATCH NAME
    Given Admin creates GET Request with valid Batch Name
    When Admin sends Get batch Request with endpoint for batch module
    Then Admin receives 200 Status code with response body

  #get batch by program id

  Scenario: Check if admin able to retrieve a batch with valid Program ID
    Given Admin creates GET Request with valid Program Id
    When Admin sends HTTPS Request to get batch by program Id with endpoint
    Then Admin receives status code 200 OK Status with response body

  #Invalid HTTP Methods for GET Endpoints
  @NegativeGet
  Scenario: Validate invalid HTTP methods used for GET batch endpoints
    Given Admin prepares the following invalid method test data
      | endpointType    | method | paramValue |
      | getByBatchId    | POST   | 123        |
      | getByBatchName  | PUT    | AlphaBatch |
      | getByProgramId  | DELETE | 456        |
    When Admin sends request to batch endpoint using invalid method
    Then Admin receives error response with 405 or appropriate status

      #Empty Values in GET Parameters
  @NegativeGet
  Scenario: Validate GET requests with empty or missing parameter values
    Given Admin prepares the following invalid parameter test data
      | endpointType    | method | paramValue |
      | getByBatchId    | GET    |            |
      | getByBatchName  | GET    |            |
      | getByProgramId  | GET    |            |
    When Admin sends request to batch endpoint with empty parameter
    Then Admin receives error response with 400 or appropriate status

  #update batch by Id

  Scenario: Check if admin able to update a Batch with valid batchID and mandatory fields in request body
    Given Admin creates PUT Request with valid BatchId and Data
    When Admin sends HTTPS Request to update with endpoint
    Then Admin receives 200 OK Status with updated value in response body for update request.

  #delete batch by Id
  #
  #Scenario: Check if admin able to delete a Batch with valid Batch ID
    #Given Admin creates DELETE Request with valid BatchId
    #When Admin sends HTTPS Request to delete with endpoint
    #Then Admin receives 200 Ok status with message to delete batch module
#
    #get by id after deleting
   #
    #Scenario: Check if admin able to retrive a batch after deleting the batch
    #Given Admin creates GET Request with valid Batch ID
    #When Admin sends HTTPS GET Request with valid id
    #Then Admin receives 200 OK Status with  batchStatus field "Inactive" in the response body.
    #
