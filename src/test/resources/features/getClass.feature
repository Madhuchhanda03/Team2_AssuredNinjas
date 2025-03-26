#Author: Subhadra
Feature: GET Class Feature

  Background: 
    When Admin sets Authorization to retrieve get requests for Class module
    
###################GET REQUEST (All Classes)#############################
  Scenario: Check if Admin able to retrieve all classes with valid Endpoint
    Given Admin creates GETAll Request for the LMS ClassApi module
    When Admin sends "GET" HTTPS Request with "getAllClass" endpoint for class module
    Then Admin receives 200  response Status code for class module

  Scenario: Check if Admin able to retrieve all classes with invalid Endpoint
    Given Admin creates GETAll Request for the LMS ClassApi module
    When Admin sends "GET" HTTPS Request with invalid endpoint for class module
   Then Admin receives 404  response Status code for class module

  Scenario: Check if Admin able to retrieve all classes with invalid Method
    Given Admin creates GETAll Request for the LMS ClassApi module
    When Admin sends "PUT" HTTPS Request with "getAllClass" endpoint for class module
    Then Admin receives 405  response Status code for class module

  Scenario: Check if Admin able to retrieve all classes without Authorization
    Given Admin creates GETAll Request for the LMS ClassAPI without Authorization
    When Admin sends "GET" HTTPS Request with "getAllClass" endpoint for class module
    #Then Admin receives 401  response Status code for class module
    #
 ##################################### GET REQUEST (class recordings by BatchId)#############
 
 Scenario: Check if admin able to retrieve class recording  with valid Batchid
    Given Admin creates GET Request with valid batchId for the LMS ClassApi module
    When Admin sends "GET" HTTPS Request with "getClassRecordingsByBatchId" endpoint for class module
    Then Admin receives 200  response Status code for class module
    
 Scenario: Check if admin able to retrieve class recording  with invalid Batchid
    Given Admin creates GET Request with invalid batchId for the LMS ClassApi module
    When Admin sends "GET" HTTPS Request with "getClassRecordingsByInvalidBatchId" endpoint for class module
    Then Admin receives 404  response Status code for class module
    
 Scenario: Check if admin able to retrieve class recording  with invalid end point
    Given Admin creates GET Request with valid batchId for the LMS ClassApi module
    When Admin sends "GET" HTTPS Request with invalid endpoint for class module
    Then Admin receives 404  response Status code for class module
    
  Scenario: Check if admin able to retrieve class recording by batchId with invalid Method
    Given Admin creates GET Request with valid batchId for the LMS ClassApi module
    When Admin sends "PUT" HTTPS Request with "getClassRecordingsByBatchId" endpoint for class module
    Then Admin receives 405  response Status code for class module
    
   Scenario: Check if admin able to retrieve class recording by batchId without Authorization
    Given Admin creates GET Request for the LMS ClassAPI without Authorization
    When Admin sends "GET" HTTPS Request with "getClassRecordingsByBatchId" endpoint for class module
    Then Admin receives 401  response Status code for class module
    
  ###################################### GET REQUEST (Class details by classId)#####################
Scenario: Check if admin is able to retrieve class details with valid classsId
    Given Admin creates GET Request with valid classsId for the LMS ClassApi module
    When Admin sends "GET" HTTPS Request with "getClassDetailsById" endpoint for class module
    Then Admin receives 200  response Status code for class module
    
 Scenario: Check if admin able to retrieve class details  with invalid classsId
    Given Admin creates GET Request with invalid classId for the LMS ClassApi module
    When Admin sends "GET" HTTPS Request with "getClassDetailsByInvalidClassId" endpoint for class module
    Then Admin receives 404  response Status code for class module
    
 Scenario: Check if admin able to retrieve class details with invalid end point
    Given Admin creates GET Request with valid classsId for the LMS ClassApi module
    When Admin sends "GET" HTTPS Request with invalid endpoint for class module
    Then Admin receives 404  response Status code for class module
    
  Scenario: Check if admin able to retrieve class details by classId with invalid Method
    Given Admin creates GET Request with valid classsId for the LMS ClassApi module
    When Admin sends "PUT" HTTPS Request with "getClassDetailsById" endpoint for class module
    Then Admin receives 405  response Status code for class module
    
   Scenario: Check if admin able to retrieve class details by classId without Authorization
    Given Admin creates GET Request for the LMS ClassAPI without Authorization
    When Admin sends "GET" HTTPS Request with "getClassDetailsById" endpoint for class module
    Then Admin receives 401  response Status code for class module

################################## GET REQUEST (all Classes by Class topic)###############

Scenario: Check if admin is able to retrieve allclasses with valid classtopic
    Given Admin creates GET Request with valid classtopic for the LMS ClassApi module
    When Admin sends "GET" HTTPS Request with "getAllClassByTopic" endpoint for class module
    Then Admin receives 200  response Status code for class module
    
 Scenario: Check if admin able to retrieve class allclasses with invalid classtopic
    Given Admin creates GET Request with invalid classtopic for the LMS ClassApi module
    When Admin sends "GET" HTTPS Request with "getClassDetailsByInvalidClassTopic" endpoint for class module
    Then Admin receives 404  response Status code for class module
    
 Scenario: Check if admin able to retrieve class details with invalid end point
    Given Admin creates GET Request with valid classtopic for the LMS ClassApi module
    When Admin sends "GET" HTTPS Request with invalid endpoint for class module
    Then Admin receives 404  response Status code for class module
    
  Scenario: Check if admin able to retrieve class details by classtopic with invalid Method
    Given Admin creates GET Request with valid classtopic for the LMS ClassApi module
    When Admin sends "PUT" HTTPS Request with "getAllClassByTopic" endpoint for class module
    Then Admin receives 405  response Status code for class module
    
   Scenario: Check if admin able to retrieve class details by classtopic without Authorization
    Given Admin creates GET Request for the LMS ClassAPI without Authorization
    When Admin sends "GET" HTTPS Request with "getAllClassByTopic" endpoint for class module
    Then Admin receives 401  response Status code for class module

################################## GET REQUEST (all Classes by BatchId)###############

Scenario: Check if admin is able to retrieve allclasses with valid BatchId
    Given Admin creates GET Request for allclasses with valid BatchId for the LMS ClassApi module
    When Admin sends "GET" HTTPS Request with "getAllClassByBatchId" endpoint for class module
    Then Admin receives 200  response Status code for class module
    
 Scenario: Check if admin able to retrieve allclasses with invalid BatchId
    Given Admin creates GET Request to retrieve all classes with invalid BatchId for the LMS ClassApi module
    When Admin sends "GET" HTTPS Request with "getAllClassByInvalidBatchId" endpoint for class module
    Then Admin receives 404  response Status code for class module
    
 Scenario: Check if admin able to retrieve allclass by BatchId details with invalid end point
    Given Admin creates GET Request for allclasses with valid BatchId for the LMS ClassApi module
    When Admin sends "GET" HTTPS Request with invalid endpoint for class module
    Then Admin receives 404  response Status code for class module
    
  Scenario: Check if admin able to retrieve allclass details by batchId with invalid Method
    Given Admin creates GET Request with valid classtopic for the LMS ClassApi module
    When Admin sends "PUT" HTTPS Request with "getAllClassByBatchId" endpoint for class module
    Then Admin receives 405  response Status code for class module
    
   Scenario: Check if admin able to retrieve class details by classtopic without Authorization
    Given Admin creates GET Request for the LMS ClassAPI without Authorization
    When Admin sends "GET" HTTPS Request with "getAllClassByBatchId" endpoint for class module
    Then Admin receives 401  response Status code for class module

###########################GET REQUEST (all Classes by StaffId)################
Scenario: Check if admin is able to retrieve allclasses with valid StaffId
    Given Admin creates GET Request for allclasses with valid StaffId for the LMS ClassApi module
    When Admin sends "GET" HTTPS Request with "getAllClassByStaffId" endpoint for class module
    Then Admin receives 200  response Status code for class module
    
 Scenario: Check if admin able to retrieve allclasses with invalid StaffId
    Given Admin creates GET Request to retrieve all classes with invalid StaffId for the LMS ClassApi module
    When Admin sends "GET" HTTPS Request with "getAllClassByInvalidStaffId" endpoint for class module
    Then Admin receives 404  response Status code for class module
    
 Scenario: Check if admin able to retrieve allclass by StaffId with invalid end point
    Given Admin creates GET Request for allclasses with valid StaffId for the LMS ClassApi module
    When Admin sends "GET" HTTPS Request with invalid endpoint for class module
    Then Admin receives 404  response Status code for class module
    
  Scenario: Check if admin able to retrieve allclass details by StaffId with invalid Method
    Given Admin creates GET Request for allclasses with valid StaffId for the LMS ClassApi module
    When Admin sends "PUT" HTTPS Request with "getAllClassByStaffId" endpoint for class module
    Then Admin receives 405  response Status code for class module
    
   Scenario: Check if admin able to retrieve class details by StaffId without Authorization
    Given Admin creates GET Request for the LMS ClassAPI without Authorization
    When Admin sends "GET" HTTPS Request with "getAllClassByStaffId" endpoint for class module
    Then Admin receives 401  response Status code for class module

######################### GET REQUEST (all recordings)#########################
    Scenario: Check if Admin able to retrieve class recordings with valid Endpoint
    Given Admin creates GETAll Request for the LMS ClassApi module
    When Admin sends "GET" HTTPS Request with "getAllRecordings" endpoint for class module
    Then Admin receives 200  response Status code for class module

  Scenario: Check if Admin able to retrieve all class recordings with invalid Endpoint
    Given Admin creates GETAll Request for the LMS ClassApi module
    When Admin sends "GET" HTTPS Request with invalid endpoint for class module
   Then Admin receives 404  response Status code for class module

  Scenario: Check if Admin able to retrieve all class recordings with invalid Method
    Given Admin creates GETAll Request for the LMS ClassApi module
    When Admin sends "PUT" HTTPS Request with "getAllRecordings" endpoint for class module
    Then Admin receives 405  response Status code for class module

  Scenario: Check if Admin able to retrieve all class recordings without Authorization
    Given Admin creates GETAll Request for the LMS ClassAPI without Authorization
    When Admin sends "GET" HTTPS Request with "getAllRecordings" endpoint for class module
    Then Admin receives 401  response Status code for class module
    
###################################GET REQUEST (Class Recordings by Class Id)#########
  Scenario: Check if admin is able to retrieve class recordings with valid Class Id
    Given Admin creates GET Request for class recordings with valid Class Id for the LMS ClassApi module
    When Admin sends "GET" HTTPS Request with "getAllClassRecordingsByClassId" endpoint for class module
    Then Admin receives 200  response Status code for class module
    
  Scenario: Check if admin able to retrieve class recordings with invalid Class Id
    Given Admin creates GET Request to retrieve all classes with invalid ClassId for the LMS ClassApi module
    When Admin sends "GET" HTTPS Request with "getAllClassRecordingsByInvalidClassId" endpoint for class module
    Then Admin receives 404  response Status code for class module
    
 Scenario: Check if admin able to retrieve class recordings with invalid end point
    Given Admin creates GET Request for class recordings with valid Class Id for the LMS ClassApi module
    When Admin sends "GET" HTTPS Request with invalid endpoint for class module
    Then Admin receives 404  response Status code for class module
    
  Scenario: Check if admin able to retrieve class recordings by ClassId with invalid Method
    Given Admin creates GET Request for class recordings with valid Class Id for the LMS ClassApi module
    When Admin sends "PUT" HTTPS Request with "getAllClassRecordingsByClassId" endpoint for class module
    Then Admin receives 405  response Status code for class module
    
   Scenario: Check if admin able to retrieve class recordings by ClassId without Authorization
    Given Admin creates GET Request for the LMS ClassAPI without Authorization
    When Admin sends "GET" HTTPS Request with "getAllClassRecordingsByClassId" endpoint for class module
    Then Admin receives 401  response Status code for class module





  