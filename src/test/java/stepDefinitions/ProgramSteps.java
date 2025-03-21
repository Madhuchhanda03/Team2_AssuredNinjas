package stepDefinitions;

import java.io.IOException;
import java.util.HashMap;

import endpoints.ApiEndPoints;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import payload.ProgramPayload;
import utils.CommonUtils;
import static io.restassured.RestAssured.given;

import org.testng.Assert;
//import  org.testng.Assert.assertEquals;

public class ProgramSteps extends CommonUtils {
	RequestSpecification request;
	ResponseSpecification resspec;
	Response response;
	ProgramPayload payload;
	String loginToken;
	// CommonUtils commonUtils;

	@When("Admin sets Authorization")
	public void admin_sets_authorization() throws IOException {

		// commonUtils = new CommonUtils();

		HashMap<String, String> map = new HashMap<>();
		map.put("userLoginEmailId", "sdetorganizer@gmail.com");
		map.put("password", "Apiphase@2");

		ApiEndPoints endPoint = ApiEndPoints.valueOf("userLogin");
		request = given().spec(requestSpecification()).body(map);
		resspec = new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();
		response = request.when().post(endPoint.getEndPoint());
		loginToken = response.jsonPath().getString("token");
		System.out.println(response.getStatusCode());

	}

	@Given("Admin creates POST Request for the LMS with request body")
	public void admin_creates_post_request_for_the_lms_with_request_body() throws IOException {
		payload = new ProgramPayload();
		String programDescription = "JavaProgram";
		String programName = "SDETJava";
		String programStatus = "Active";
		request = given().spec(requestSpecification())
				.body(payload.addNewProgram(programDescription, programName, programStatus))
				.header("Authorization", "Bearer " + loginToken);

	}

	@When("Admin sends HTTPS Request and  request Body with endpoin")
	public void admin_sends_https_request_and_request_body_with_endpoin() {
		ApiEndPoints addProgramEndpoint = ApiEndPoints.valueOf("addNewProgram");
		System.out.println(addProgramEndpoint.getEndPoint());
		response = request.when().post(addProgramEndpoint.getEndPoint());
	}

	@Then("Admin receives {int} Created Status with response body.")
	public void admin_receives_created_status_with_response_body(int Statuscode) {
		Assert.assertEquals(response.getStatusCode(), Statuscode);
	}

}
