package stepDefinitions;

import endpoints.ApiEndPoints;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.Assert;
import payload.BatchPayload;
//import payload.ProgramPayload;

import io.cucumber.datatable.DataTable;
import payload.ProgramPayload;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ProgramBatchModuleSD {
    List<Map<String, String>> invalidMethodTestData;
    List<Map<String, String>> invalidParamTestData;

    BatchPayload batchPayload = new BatchPayload();
    ProgramPayload programPayload = new ProgramPayload();



    @Given("Admin creates POST Request  with existing value in request body")
    public void admin_creates_post_request_with_existing_value_in_request_body() throws IOException {
        batchPayload.getLoginToken();
        batchPayload.createBatchWithExistingBatchName();
    }

    @When("Admin sends HTTPS POST Request with endpoint to create batch")
    public void admin_sends_https_post_request_with_endpoint_to_create_batch() {
        batchPayload.batchResponse("POST", ApiEndPoints.CREATEBATCH);
    }

    @Then("Admin receives {int} Bad Request Status with message in the response body")
    public void admin_receives_bad_request_status_with_message_in_the_response_body(Integer int1) {
        Assert.assertEquals(batchPayload.response.getStatusCode(), 400);
        System.out.println("Expected 400 Bad Request received");

    }

    @Given("Admin creates POST Request  with invalid data in request body")
    public void admin_creates_post_request_with_invalid_data_in_request_body() throws IOException {
        batchPayload.createBatchWithMissingMandatoryFields();
    }

    @When("Admin sends POST Request with endpoint to create batch")
    public void admin_sends_post_request_with_endpoint_to_create_batch() {
        batchPayload.batchResponse("POST", ApiEndPoints.CREATEBATCH);
    }

    @Then("Admin receives {int} Bad Request Status with message")
    public void admin_receives_bad_request_status_with_message(Integer expectedStatusCode) {
        Assert.assertEquals(batchPayload.response.getStatusCode(), expectedStatusCode.intValue());
        System.out.println("Received expected status code: " + expectedStatusCode);

        // Optional: Print error message
        String errorMsg = batchPayload.response.jsonPath().getString("message");
        System.out.println("Error Message: " + errorMsg);
    }

    @Given("Admin creates POST Request")
    public void admin_creates_post_request() throws IOException {
        batchPayload.createBatchFromExcel("BatchPostdata");
    }

    @When("Admin sends HTTPS Request with invalid endpoint")
    public void admin_sends_https_request_with_invalid_endpoint() throws IOException {
        batchPayload.requestWithInvalidEndpoint();
    }

    @Then("Admin receives {int} not found Status")
    public void admin_receives_not_found_status(Integer expectedStatusCode) {
        Assert.assertEquals(batchPayload.response.getStatusCode(), expectedStatusCode.intValue());
        System.out.println("Invalid Endpoint Response: " + batchPayload.response.getBody().asPrettyString());
    }

    @Given("Admin creates POST Request with missing additional fields")
    public void admin_creates_post_request_with_missing_additional_fields() throws IOException {
        batchPayload.createBatchWithMissingMandatoryFields();
    }

    @When("Admin sends HTTPS Request with valid endpoint to create batch")
    public void admin_sends_https_request_with_valid_endpoint_to_create_batch() {
        batchPayload.batchResponse("POST", ApiEndPoints.CREATEBATCH); // Valid endpoint POST
    }

    @Then("Admin receives {int} Created Status with response")
    public void admin_receives_created_status_with_response(Integer expectedStatusCode) {
        Assert.assertEquals(batchPayload.response.getStatusCode(), expectedStatusCode.intValue());
        System.out.println("Batch Created With Missing Fields: " + batchPayload.response.asPrettyString());

        // Validate against schema
        batchPayload.validateBatchSchema();
    }

    @Given("Admin creates POST Request with invalid data fields in request body")
    public void admin_creates_post_request_with_invalid_data_fields_in_request_body() throws IOException {
        batchPayload.createBatchWithInvalidDataFormat();
    }

    @When("Admin sends HTTPS POST Request with valid endpoint")
    public void admin_sends_https_post_request_with_valid_endpoint() {
        batchPayload.batchResponse("POST", ApiEndPoints.CREATEBATCH);
    }

    @Then("Admin receives {int} Bad Request Status with message and boolean success")
    public void admin_receives_bad_request_status_with_message_and_boolean_success(Integer int1) {
        Assert.assertEquals(batchPayload.response.getStatusCode(), 400);
        System.out.println("Received expected status code: 400");
    }

    @Given("Admin creates GET Request")
    public void admin_creates_get_request() throws IOException {
        batchPayload.getLoginToken();
    }

    @When("Admin sends HTTPS Get Request with endpoint to get batch")
    public void admin_sends_https_get_request_with_endpoint_to_get_batch() throws IOException {
        batchPayload.getAllBatches();
    }

    @Then("Admin receives {int} OK Status with response body")
    public void admin_receives_ok_status_with_response_body(Integer int1) {
        Assert.assertEquals(batchPayload.response.getStatusCode(), 200);
        System.out.println("All Batches Response:\n" + batchPayload.response.asPrettyString());
    }

    @Given("Admin creates GET Request with invalid endpoint")
    public void admin_creates_get_request_with_invalid_endpoint() throws IOException {
        batchPayload.getLoginToken();
    }

    @When("Admin sends HTTPS GET Request with invalid endpoint to get all batches")
    public void admin_sends_https_get_request_with_invalid_endpoint_to_get_all_batches() throws IOException {
        batchPayload.getAllBatchesInvalidEndpoint();
    }

    @Then("Admin receives {int} status with error message Not Found")
    public void admin_receives_status_with_error_message_not_found(Integer int1) {
        Assert.assertEquals(batchPayload.response.getStatusCode(), 404);
       // Assert.assertTrue(batchPayload.response.asString().contains("Not Found"));
        System.out.println("Error Response:\n" + batchPayload.response.asPrettyString());
    }

    @Given("Admin creates GET Request without authorization")
    public void admin_creates_get_request_without_authorization() {
        // No login token
    }

    @When("Admin sends HTTPS Get Request to get batch without authorization")
    public void admin_sends_https_get_request_to_get_batch_without_authorization() throws IOException {
        batchPayload.getAllBatchesWithoutAuth();
    }

    @Then("Admin receives 401 Unauthorized Status with error message")
    public void admin_receives_401_unauthorized_status_with_error_message() {
        Assert.assertEquals(batchPayload.response.getStatusCode(), 401);
//        Assert.assertTrue(batchPayload.response.asString().toLowerCase().contains("unauthorized"),
//                "Expected error message to contain 'Unauthorized'. Actual: " + batchPayload.response.asString());
    }


    @Given("Admin creates GET Request with valid Batch ID")
    public void admin_creates_get_request_with_valid_batch_id() throws IOException {
        batchPayload.getBatchById();
    }

    @When("Admin sends Get Request with endpoint for batch module")
    public void admin_sends_get_request_with_endpoint_for_batch_module() {
      //already called in Given step
    }

    @Then("Admin receives {int} OK Status with response in the response body")
    public void admin_receives_ok_status_with_response_in_the_response_body(Integer statusCode) {
        Assert.assertEquals(batchPayload.response.getStatusCode(), statusCode.intValue());
    }

    @Given("Admin creates GET Request with valid Batch Name")
    public void admin_creates_get_request_with_valid_batch_name() throws IOException {
        batchPayload.getBatchByName();
    }

    @When("Admin sends Get batch Request with endpoint for batch module")
    public void admin_sends_get_batch_request_with_endpoint_for_batch_module() throws IOException {
        System.out.println("Sent GET request to fetch batch by name.");
    }

    @Then("Admin receives {int} Status code with response body")
    public void admin_receives_status_code_with_response_body(Integer expectedStatusCode) {
        int actualStatusCode = batchPayload.response.getStatusCode();
        Assert.assertEquals(actualStatusCode, expectedStatusCode.intValue(),
                " Status code mismatch for Get Batch By Name");
        System.out.println("Response Status Code: " + actualStatusCode);

        // Optional: Print a quick batch name from response
        String returnedBatchName = batchPayload.response.jsonPath().getString("batchName");
        System.out.println("Returned Batch Name: " + returnedBatchName);
    }

    @Given("Admin creates GET Request with valid Program Id")
    public void admin_creates_get_request_with_valid_program_id() throws IOException {
        batchPayload.getBatchByProgramId();
    }

    @When("Admin sends HTTPS Request to get batch by program Id with endpoint")
    public void admin_sends_https_request_to_get_batch_by_program_id_with_endpoint() {
        // Already sent in the above method — logically keeping this step
        System.out.println("Sent GET request for batch by program ID.");
    }

    @Then("Admin receives status code {int} OK Status with response body")
    public void admin_receives_status_code_ok_status_with_response_body(Integer int1) {
        int statusCode = batchPayload.response.getStatusCode();
        Assert.assertEquals(statusCode, 200, "Expected status code 200 but got " + statusCode);

        // Optionally validate schema
        batchPayload.response.then().assertThat()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("../batch_schema.json"));

        //System.out.println("Retrieved batch using program ID: " + batchPayload.programPayload.programId);

    }

    @Given("Admin prepares the following invalid method test data")
    public void admin_prepares_the_following_invalid_method_test_data(DataTable dataTable) {
        invalidMethodTestData = dataTable.asMaps(String.class, String.class);
    }

    @When("Admin sends request to batch endpoint using invalid method")
    public void admin_sends_request_to_batch_endpoint_using_invalid_method() throws IOException {
        batchPayload.getLoginToken();
        for (Map<String, String> row : invalidMethodTestData) {
            String endpointType = row.get("endpointType");
            String method = row.get("method");
            String paramValue = row.get("paramValue");

            batchPayload.sendInvalidHttpMethod(endpointType, method, paramValue);
        }
    }

    @Then("Admin receives error response with 405 or appropriate status")
    public void admin_receives_error_response_with_405_or_appropriate_status() throws IOException {

        int statusCode = batchPayload.response.statusCode();
        Assert.assertTrue(
                statusCode == 405 || statusCode == 400 || statusCode == 404,
                "Expected 405, 400, or 404 but got: " + statusCode
        );

        System.out.println(" Invalid method response status verified: " + statusCode);
    }

    @Given("Admin prepares the following invalid parameter test data")
    public void admin_prepares_the_following_invalid_parameter_test_data(DataTable dataTable) {
        invalidParamTestData = dataTable.asMaps(String.class, String.class);
    }

    @When("Admin sends request to batch endpoint with empty parameter")
    public void admin_sends_request_to_batch_endpoint_with_empty_parameter() throws IOException {
       // String token = batchPayload.getLoginToken();
        for (Map<String, String> row : invalidParamTestData) {
            String endpointType = row.get("endpointType");
            String method = row.get("method");
            String paramValue = row.get("paramValue");

            batchPayload.sendGetRequestWithEmptyParam(endpointType, method, paramValue);
        }
    }

    @Then("Admin receives error response with 400 or appropriate status")
    public void admin_receives_error_response_with_400_or_appropriate_status() {
        int statusCode = batchPayload.response.getStatusCode();
        Assert.assertTrue(
                statusCode == 400 || statusCode == 404 || statusCode == 500,
                "Expected 400/404/500, but got: " + statusCode
        );
        System.out.println("Empty param validation - Response status: " + statusCode);
    }

    @Given("Admin creates PUT Request with valid BatchId and Data")
    public void admin_creates_put_request_with_valid_batch_id_and_data() throws IOException {
        batchPayload.updateBatchById();
    }

    @When("Admin sends HTTPS Request to update with endpoint")
    public void admin_sends_https_request_to_update_with_endpoint() {
        System.out.println("Update request sent to endpoint.");
    }

    @Then("Admin receives {int} OK Status with updated value in response body for update request.")
    public void admin_receives_ok_status_with_updated_value_in_response_body_for_update_request(Integer statusCode) {
        // Assert response status
        Assert.assertEquals(batchPayload.response.statusCode(), 200);

        // Optional: Check response contains updated batch name or classes
        String updatedName = batchPayload.createdBatchPojo.getBatchName();
        String responseName = batchPayload.response.jsonPath().getString("batchName");
        Assert.assertEquals(responseName, updatedName);

        System.out.println("Batch update verified. Updated name: " + responseName);
    }


}
