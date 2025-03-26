package stepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import payload.ProgramPayload;
import utils.CommonUtils;

public class ProgramSteps {
	ProgramPayload programPlayload;
	CommonUtils commonUtil;

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
	public void admin_sends_https_request_and_request_body_with_endpoint(String method, String endPoint) {
		programPlayload.ProgramResponse(method, endPoint);
	}

	@When("Admin sends {string} HTTPS Request and  request Body with invalid endpoint")
	public void admin_sends_https_request_and_request_body_with_invalid_endpoint(String string) {
		programPlayload.requestWithInvalidEndpoint();
	}

	@Given("Admin creates POST Request for the LMS with invalid request body")
	public void admin_creates_post_request_for_the_lms_with_invalid_request_body() throws IOException {

		programPlayload.createProgramWithInvalidRequestBody();
	}

	@Given("Admin creates POST Request for the LMS with missing values in the request body")
	public void admin_creates_post_request_for_the_lms_with_missing_values_in_the_request_body() throws IOException {
		programPlayload.programWithMissingValue();
	}

	@Given("Admin creates POST Request for the LMS with request body with missing additional field")
	public void admin_creates_post_request_for_the_lms_with_request_body_with_missing_additional_field()
			throws IOException {
		programPlayload.createProgramWithMissingValueInAdditionalField();
	}

	@When("Admin sends {string} HTTPS Request and  request Body with {string} valid scenario endpoint")
	public void admin_sends_https_request_and_request_body_with_valid_scenario_endpoint(String method,
			String endpoint) {
		programPlayload.validPostHttpRequest(method, endpoint);
	}

	// *********************GET REQUEST********************************************
	@Given("Admin creates GET Request for the LMS API")
	public void admin_creates_get_request_for_the_lms_api() throws IOException {
		programPlayload.programGetRequest();

	}

	@When("Admin sends {string} HTTPS Request with {string} endpoint")
	public void admin_sends_https_request_with_endpoint(String method, String endPoint) {
		programPlayload.ProgramResponse(method, endPoint);

	}

	@Then("Admin receives {int} Status code")
	public void admin_receives_status_code(int statuscode) {
		Assert.assertEquals(programPlayload.response.getStatusCode(), statuscode);
	}

	@When("Admin sends {string} HTTPS Request with invalid endpoint")
	public void admin_sends_https_request_with_invalid_endpoint(String string) {
		programPlayload.getRequestWithInvalidEndpoint();
	}

	@Given("Admin creates GET Request for the LMS API without Authorization")
	public void admin_creates_get_request_for_the_lms_api_without_authorization() throws IOException {
		programPlayload.getAllProgramWithoutAuthorization();
	}
	// *********************GET REQUEST BY PROGRAM ID *****************************

	@Given("Admin creates GET Request for the LMS API with invalid baseURI")
	public void admin_creates_get_request_for_the_lms_api_with_invalid_base_uri() throws IOException {

		programPlayload.withInvalidBaseUri();
	}

	@When("Admin sends HTTPS Request with {string} endpoint")
	public void admin_sends_https_request_with_endpoint(String endpoint) {
		programPlayload.getRequestWithInvalidProgramId(endpoint);
	}

	@When("Admin sends HTTPS Request with {string} invalid endpoint")
	public void admin_send_https_request_with_invalid_endpoint(String endpoint) {
		programPlayload.getRequestWithInvalidProgramId(endpoint);
	}

	@When("Admin sends {string} HTTPS Request with {string} valid endpoint")
	public void admin_sends_https_request_with_valid_endpoint(String method, String endpoint) {
		programPlayload.getResponseWithProgramId(method, endpoint);
	}

	@Then("Admin receives {int} not found Status code")
	public void admin_receives_not_found_status_code(int statuscode) {
		Assert.assertEquals(programPlayload.statusCode, statuscode);
	}

//************* PUT REQUEST BY PROGRAMID *********************************

	@Given("Admin creates PUT Request for the LMS API endpoint with request Body with mandatory , additional  fields")
	public void admin_creates_put_request_for_the_lms_api_endpoint_with_request_body_with_mandatory_additional_fields()
			throws IOException {
		programPlayload.updateProgramWithValidPayload();
	}

	@When("Admin sends HTTPS Request with {string} valid put endpoint")
	public void admin_sends_https_request_with_valid_put_endpoint(String endpoint) {
		programPlayload.putResponseWithProgramId(endpoint);
	}

	
	@Given("Admin creates PUT Request for the LMS API  with invalid request body")
	public void admin_creates_put_request_for_the_lms_api_with_invalid_request_body() throws IOException {
		programPlayload.updateWithInvalidRequestBody();
	}

	@Given("Admin creates PUT Request for the LMS API  with missing mandatory fields")
	public void admin_creates_put_request_for_the_lms_api_with_missing_mandatory_fields() throws IOException {
		programPlayload.updateWithMissingValue();
	}
	@When("Admin sends HTTPS Request with invalid endpoint and invalid programId")
	public void admin_sends_https_request_with_invalid_endpoint_and_invalid_program_id() {
		programPlayload.putRequestWithInvalidEndpoint();
	}


}
