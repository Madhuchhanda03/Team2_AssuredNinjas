package stepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import payload.ProgramPayload;
import utils.CommonUtils;

public class DeleteSteps  {
	ProgramPayload programPlayload;
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

}
