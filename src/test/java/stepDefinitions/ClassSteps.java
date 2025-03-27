package stepDefinitions;
import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import payload.ClassPayload;


public class ClassSteps {
ClassPayload classpayload;
	
	@When("Admin sets Authorization")
	public void admin_sets_authorization() throws IOException {
		classpayload = new ClassPayload();
		classpayload.getLoginToken();
	}
	@Given("Admin creates post request for the LMS with request body")
	public void admin_creates_post_request_for_the_lms_with_request_body() throws IOException {
	    classpayload.createClassPayload();
	    
	}

//	@When("Admin calls {string} HTTPS Request and  request Body with {string}")
//	public void admin_calls_post_request_with_endpoint(String method,String endPoint ) {
//	    classpayload.ClassResponse(method,endPoint);
//	}
//
//	@Then("Admin receives {int} statuscode")
//	public void admin_receives_create_request(int statuscode) {
//		Assert.assertEquals(classpayload.response.getStatusCode(), statuscode); 
//	}
	@When("Admin calls {string} Http request with {string} endpoint")
	public void admin_calls_http_request_with_endpoint(String method, String endpoint) {
		classpayload.ClassResponse(method,endpoint);
	}

	@Then("Admin receives {int} create request")
	public void admin_receives_create_request(Integer statuscode) {
		Assert.assertEquals(classpayload.response.getStatusCode(), statuscode);  
	}

}


