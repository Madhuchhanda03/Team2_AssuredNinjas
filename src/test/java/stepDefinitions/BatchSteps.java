package stepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import payload.BatchPayload_03;
import payload.ProgramPayload;

public class BatchSteps {
	ProgramPayload programPlayload;
	BatchPayload_03 batchPayload;
	@Given("Admin create POST Request  with valid data in request body")
	public void admin_create_post_request_with_valid_data_in_request_body() throws IOException {
		programPlayload = new ProgramPayload();
		batchPayload=new BatchPayload_03();
		programPlayload.getLoginToken(); 
		batchPayload.createBatchPayload();
	}

	@When("Admin sends {string} HTTPS Request with valid {string} endpoint")
	public void admin_sends_https_request_with_valid_endpoint(String method,String endpoint) {
		batchPayload.batchPostHttpRequest(method,endpoint);
	}

	@Then("Admin receives {int} Created Status with response body.")
	public void admin_receives_created_status_with_response_body(int statusCode) {
		Assert.assertEquals(batchPayload.response.getStatusCode(), statusCode);
	}

}
