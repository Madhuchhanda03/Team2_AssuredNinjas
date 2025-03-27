
@tag
Feature: Batch Features

  @tag1
  Scenario: Check if admin able to create a Batch with valid endpoint and request body (non existing values)
Given Admin create POST Request  with valid data in request body
When Admin sends "POST" HTTPS Request with valid "createNewBatch" endpoint
Then Admin receives 201 Created Status with response body.