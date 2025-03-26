
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