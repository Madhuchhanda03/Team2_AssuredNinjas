
@tag
Feature: delete program

  @tag1
  Scenario: Check if Admin able to delete a program with valid programName 
  Given 	Admin creates DELETE Request for the LMS API endpoint  and  valid programName
  When 	Admin send HTTPS Request with "deleteProgramByprogramName" endpoint
 	Then Admin receives 200 Ok status code


  @tag2
  Scenario: Check if Admin able to delete a program with valid program ID
 Given  Admin creates DELETE Request for the LMS API endpoint  and  valid program ID
  When Admin send HTTPS Request with "deleteProgramByprogramId" endpoints
 	Then Admin receives 200 Ok status code


   ####  delete batch by Id

  Scenario: Check if admin able to delete a Batch with valid Batch ID
    Given Admin creates DELETE Request with valid BatchId
    When Admin sends HTTPS Request to delete with endpoint
    Then Admin receives 200 Ok status with message to delete batch module

   #### get batch by id after deleting

  Scenario: Check if admin able to retrive a batch after deleting the batch
    Given Admin creates GET Request with valid Batch ID
    When Admin sends HTTPS GET Request with valid id
    Then Admin receives 200 OK Status with  batchStatus field "Inactive" in the response body.