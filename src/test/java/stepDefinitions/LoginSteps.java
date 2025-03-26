package stepDefinitions;


import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import payload.LoginPayload;
import pojo.LoginRequest;
import pojo.LoginResponse;
import utils.ExcelReader;

public class LoginSteps {
    
    private LoginResponse loginResponse = null;
    public static String Token;

    @Given("User provides {string} credentials")
    public void user_provides_credentials_from(String scenario)throws IOException {
    	LoginPayload lp = new LoginPayload();
    	loginResponse = lp.login( scenario);
    	
    }
    @When("User sends a POST request to endpoint")
    public void user_sends_post_request_to_endpoint() {
        Assert.assertNotNull(loginResponse);
    }

    @Then("API should respond with expected status {int}")
    public void api_should_respond_with_status(int statusCode) {
        Assert.assertEquals(statusCode, loginResponse.getStatusCode());
    }
    

}


