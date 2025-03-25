package stepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import payload.GetClassPayload;
import payload.ProgramPayload;


public class getClassSteps{
	
	GetClassPayload getClassPayload;
	ProgramPayload programPayload;
	@When("Admin sets Authorization to retrieve get requests for Class module")
	public void admin_sets_authorization_to_retrieve_get_requests_for_class_module() throws IOException {
		getClassPayload = new GetClassPayload();
		programPayload = new ProgramPayload();
		//getClassPayload.getLoginToken();
		programPayload.getLoginToken();
	}

	@Given("Admin creates GETAll Request for the LMS ClassApi module")
	public void admin_creates_get_all_request_for_the_lms_class_api_module() throws IOException {
		getClassPayload.ClassGetAllRequest();
	}

	@When("Admin sends {string} HTTPS Request with {string} endpoint for class module")
	public void admin_sends_https_request_with_endpoint_for_class_module(String method, String endPoint) {
		getClassPayload.ClassResponse(method, endPoint);
	}

	@Then("Admin receives {int}  response Status code for class module")
	public void admin_receives_response_status_code_for_class_module(int statuscode) {
		Assert.assertEquals(getClassPayload.response.getStatusCode(), statuscode);
	}

	@When("Admin sends {string} HTTPS Request with invalid endpoint for class module")
	public void admin_sends_https_request_with_invalid_endpoint_for_class_module(String string) {
	    getClassPayload.ClassgetRequestWithInvalidEndpoint();
	}
	@Given("Admin creates GETAll Request for the LMS ClassAPI without Authorization")
	public void admin_creates_get_all_request_for_the_lms_class_api_without_authorization() throws IOException {
		getClassPayload.ClassGetAllRequestWithoutAuthorization();
	}
	
	//#############GetClassBy BtachId####
	@Given("Admin creates GET Request with valid batchId for the LMS ClassApi module")
	public void admin_creates_get_request_with_valid_batch_id_for_the_lms_class_api_module() throws IOException {
		getClassPayload.ClassGetAllRequest();
	}
	
	@Given("Admin creates GET Request for the LMS ClassAPI without Authorization")
	public void admin_creates_get_request_for_the_lms_class_api_without_authorization() throws IOException {
		getClassPayload.ClassGetAllRequestWithoutAuthorization();
	}
	
	@Given("Admin creates GET Request with invalid batchId for the LMS ClassApi module")
	public void admin_creates_get_request_with_invalid_batch_id_for_the_lms_class_api_module() throws IOException {
		getClassPayload.ClassGetAllRequest();
	}
	
	//#######################GetClass By ClassId#######
	@Given("Admin creates GET Request with valid classsId for the LMS ClassApi module")
	public void admin_creates_get_request_with_valid_classs_id_for_the_lms_class_api_module() throws IOException {
		getClassPayload.ClassGetAllRequest();
	}
	
	@Given("Admin creates GET Request with invalid classId for the LMS ClassApi module")
	public void admin_creates_get_request_with_invalid_class_id_for_the_lms_class_api_module() throws IOException {
		getClassPayload.ClassGetAllRequest();
	}
	
	//###########################GetClass By ClassTopic############
	@Given("Admin creates GET Request with valid classtopic for the LMS ClassApi module")
	public void admin_creates_get_request_with_valid_classtopic_for_the_lms_class_api_module() throws IOException {
		getClassPayload.ClassGetAllRequest();
	}

	@Given("Admin creates GET Request with invalid classtopic for the LMS ClassApi module")
	public void admin_creates_get_request_with_invalid_classtopic_for_the_lms_class_api_module() throws IOException {
		getClassPayload.ClassGetAllRequest();
	}
	//###################GET REQUEST (all Classes by BatchId)###############
	@Given("Admin creates GET Request for allclasses with valid BatchId for the LMS ClassApi module")
	public void admin_creates_get_request_for_allclasses_with_valid_batch_id_for_the_lms_class_api_module() throws IOException {
		getClassPayload.ClassGetAllRequest();
	}

	@Given("Admin creates GET Request to retrieve all classes with invalid BatchId for the LMS ClassApi module")
	public void admin_creates_get_request_to_retrieve_all_classes_with_invalid_batch_id_for_the_lms_class_api_module() throws IOException {
		getClassPayload.ClassGetAllRequest();
	}
}
