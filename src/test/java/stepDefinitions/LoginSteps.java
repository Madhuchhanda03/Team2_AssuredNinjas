package stepDefinitions;


import io.cucumber.java.en.*;
import io.restassured.response.Response;
import payload.LoginPayload;

import org.testng.Assert;
import pojo.LoginRequest;
import pojo.LoginResponse;
import utils.ExcelReader;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class LoginSteps {
    
    private LoginResponse loginResponse = null;
    

    @Given("User provides {string} credentials from {string}")
    public void user_provides_credentials_from(String scenario, String fileName) {
        // Write code here that turns the phrase above into concrete actions
    	List<Map<String, String>> lst = ExcelReader.getAllDataFromExcel(fileName, scenario,"Login");
    	for (Iterator iterator = lst.iterator(); iterator.hasNext();) {
			Map<String, String> map = (Map<String, String>) iterator.next();
			LoginRequest loginRequest  = new LoginRequest(map.get("userLoginEmailId"), map.get("password"));

	         LoginPayload lp = new LoginPayload();
	          Response response = lp.sendPostRequest("https://lms-hackthon-feb25-803334c87fbe.herokuapp.com/lms/login", loginRequest);
	           loginResponse = new LoginResponse();
	         System.out.println("DATA********************************Start");
	         System.out.println(response.getStatusLine());
	         loginResponse.setMessage(response.asPrettyString());
	         String token = response.jsonPath().getString("token");
	         loginResponse.setStatusCode(response.getStatusCode());
	         loginResponse.setToken(token);
	         System.out.println("DATA********************************End");
			
		}
    }
    @When("User sends a POST request to {string}")
    public void user_sends_post_request(String url) {
        Assert.assertNotNull(loginResponse);
    }

    @Then("API should respond with expected status {int}")
    public void api_should_respond_with_status(int statusCode) {
        Assert.assertEquals(statusCode, loginResponse.getStatusCode());
    }
    

}

