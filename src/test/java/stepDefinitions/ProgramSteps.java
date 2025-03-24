package stepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import payload.ProgramPayload;

public class ProgramSteps {
	ProgramPayload programPlayload;

	@When("Admin sets Authorization")
	public void admin_sets_authorization() throws IOException {
		programPlayload = new ProgramPayload();
		programPlayload.getLoginToken();
	}

	@Given("Admin creates POST Request for the LMS with request body")
	public void admin_creates_post_request_for_the_lms_with_request_body() throws IOException {
		
		programPlayload.createNewProgramPayload();
		 

	}

	@When("Admin sends {string} HTTPS Request and  request Body with {string} endpoint")
	public void admin_sends_https_request_and_request_body_with_endpoint(String method,String endPoint ){
		programPlayload.ProgramResponse(method,endPoint);
	}

	@Then("Admin receives {int} Created Status with response body.")
	public void admin_receives_created_status_with_response_body(int statuscode) {
		Assert.assertEquals(programPlayload.response.getStatusCode(), statuscode);
	}

	@When("Admin sends {string} HTTPS Request and  request Body with invalid endpoint")
	public void admin_sends_https_request_and_request_body_with_invalid_endpoint(String string) {
		programPlayload.requestWithInvalidEndpoint();
	}

	@Then("Admin receives {int} not found  Status with message and boolean success details")
	public void admin_receives_not_found_status_with_message_and_boolean_success_details(int statuscode) {
		Assert.assertEquals(programPlayload.response.getStatusCode(), statuscode);
	}

	@Then("Admin receives {int} Bad Request Status with message and boolean success details")
	public void admin_receives_bad_request_status_with_message_and_boolean_success_details(int statuscode) {
		 Assert.assertEquals(programPlayload.response.getStatusCode(), statuscode);
	}

	@Then("Admin receives {int} Method Not Allowed")
	public void admin_receives_method_not_allowed(int statuscode) {
		// Assert.assertEquals(response.getStatusCode(), statuscode);
	}

	@Then("Admin receives {int} Bad Request Status")
	public void admin_receives_bad_request_status(int statuscode) {
		 Assert.assertEquals(programPlayload.response.getStatusCode(), statuscode);
	}

	@Given("Admin creates POST Request for the LMS with invalid request body")
	public void admin_creates_post_request_for_the_lms_with_invalid_request_body() throws IOException {
		
		programPlayload.createProgramWithInvalidRequestBody();
	}

	@Given("Admin creates POST Request for the LMS with missing values in the request body")
	public void admin_creates_post_request_for_the_lms_with_missing_values_in_the_request_body() {

	}

	@Given("Admin creates POST Request for the LMS with request body with missing additional field")
	public void admin_creates_post_request_for_the_lms_with_request_body_with_missing_additional_field() {
	}

}
