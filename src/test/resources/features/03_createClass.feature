#Author: Subhadra
Feature: Class Feature

  Background: 
    When Admin sets Authorization for create class module

  
  Scenario: Check if Admin able to create a class with valid endpoint and request body with Authorization
    Given Admin creates POST Request for the LMS with request body of Class module.
    When Admin sends "POST" HTTPS Request and  request Body with "addNewClass" endpoint for Class module
    Then Admin receives 201 response Status code for class.
