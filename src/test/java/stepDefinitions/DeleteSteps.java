package stepDefinitions;

import java.io.IOException;

import endpoints.ApiEndPoints;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import payload.ProgramPayload;
import utils.CommonUtils;
import payload.BatchPayload;

public class DeleteSteps  {
	ProgramPayload programPlayload;
	BatchPayload batchPayload=new BatchPayload();
	CommonUtils commonUtil;
	@Given("Admin creates DELETE Request for the LMS API endpoint  and  valid programName")
	public void admin_creates_delete_request_for_the_lms_api_endpoint_and_valid_program_name() throws IOException {
		programPlayload = new ProgramPayload();
		programPlayload.deleteByprogramName();
		
	}

	@When("Admin send HTTPS Request with {string} endpoint")
	public void admin_send_https_request_with_endpoint(String endpoint) {
		programPlayload.deleteResponseWithProgramName(endpoint);
	}

	@Then("Admin receives {int} Ok status code")
	public void admin_receives_ok_status_code(int statuscode) {
		Assert.assertEquals(programPlayload.response.getStatusCode(), statuscode);
	}

	@Given("Admin creates DELETE Request for the LMS API endpoint  and  valid program ID")
	public void admin_creates_delete_request_for_the_lms_api_endpoint_and_valid_program_id() throws IOException {
		programPlayload = new ProgramPayload();
		programPlayload.deleteByprogramName();
	}
	@When("Admin send HTTPS Request with {string} endpoints")
	public void admin_send_https_request_with_endpoints(String endpoint) {
		programPlayload.deleteResponseWithProgramId(endpoint);
	}


	//*********DELETE Batch*********


	@Given("Admin creates DELETE Request with valid BatchId")
	public void admin_creates_delete_request_with_valid_batch_id() {
		// No setup needed here if batchId is already captured from previous POST step
	}

	@When("Admin sends HTTPS Request to delete with endpoint")
	public void admin_sends_https_request_to_delete_with_endpoint() {
		batchPayload.batchResponse("DELETE", ApiEndPoints.DELETEBYBATCHID);
	}

	@Then("Admin receives 200 Ok status with message to delete batch module")
	public void admin_receives_200_ok_status_with_message_to_delete_batch_module() {
		Assert.assertEquals(batchPayload.response.getStatusCode(), 200);
		System.out.println("Deleted batch successfully: " + batchPayload.response.asString());
	}

	@Given("Admin creates GET Request with Batch ID")
	public void admin_creates_get_request_with_batch_id() {
		// Just ensure batchId is retained from earlier step
	}

	@When("Admin sends HTTPS GET Request with valid id")
	public void admin_sends_https_get_request_with_valid_id() throws IOException {
		batchPayload.getBatchById();
	}

	@Then("Admin receives 200 OK Status with  batchStatus field {string} in the response body.")
	public void admin_receives_ok_status_with_batch_status_field(String expectedStatus) {
		Assert.assertEquals(batchPayload.response.getStatusCode(), 200);
		String actualStatus = batchPayload.response.jsonPath().getString("batchStatus");
		Assert.assertEquals(actualStatus, expectedStatus);
		System.out.println("Batch status after deletion: " + actualStatus);
	}


}
