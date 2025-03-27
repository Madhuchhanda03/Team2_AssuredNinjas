package stepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import payload.CreateClassPayload;
import payload.GetClassPayload;
import payload.ProgramPayload;

public class createClassSteps {

	CreateClassPayload createClassPayload;
	//ProgramPayload programPayload;
@When("Admin sets Authorization for create class module")
public void admin_sets_authorization_for_create_class_module() throws IOException {
	createClassPayload = new CreateClassPayload();
	createClassPayload.getLoginToken();
//	programPayload = new ProgramPayload();
//	//getClassPayload.getLoginToken();
//	programPayload.getLoginToken();
	
}

@Given("Admin creates POST Request for the LMS with request body of Class module.")
public void admin_creates_post_request_for_the_lms_with_request_body_of_class_module() throws IOException {
	createClassPayload.createNewClassPayload();
}

@When("Admin sends {string} HTTPS Request and  request Body with {string} endpoint for Class module")
public void admin_sends_https_request_and_request_body_with_endpoint_for_class_module(String method, String endPoint) {
	createClassPayload.ProgramResponse(method,endPoint);
}

@Then("Admin receives {int} response Status code for class.")
public void admin_receives_response_status_code_for_class(Integer statuscode) {
	Assert.assertEquals(createClassPayload.response.getStatusCode(), statuscode);
}


}
