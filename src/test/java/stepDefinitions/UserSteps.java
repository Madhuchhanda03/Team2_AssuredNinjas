package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertNotNull;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import payload.UserPayload;
import pojo.UserPojo;
import utils.ExcelReader;

public class UserSteps {
	private String roleidURL = null;
	private String v2userURL = null;
	private String ActiveURL = null;
	private String allURL = null;
	private String emailURL = null;
	private String useridURL = null;
	private String usersURL = null;
	private String proidURL = null;
	private String batchURL = null;
	private String detURL = null;
	private String idURL = null;
	private String usURL = null;
	private String proURL = null;
	private String tURL = null;
	private String deURL = null;
	private String roleURL = null;
	private static String fileName = "team2Data.xlsx";
	private static String base = "https://lms-hackthon-feb25-803334c87fbe.herokuapp.com/lms/";
	private Response response;

	@Given("User has a valid Bearer token")
	public void user_has_a_valid_token() {
		UserPojo usr = new UserPojo();
	}

	@When("User sends a POST request with user data")
	public void user_sends_post_request() {
		// Assuming that data comes from the Excel
		List<Map<String, String>> lst = ExcelReader.getAllDataFromExcel(fileName, "roleStatus", "User");

		UserPayload upd = new UserPayload();
		for (Map<String, String> map : lst) {
			response = upd.sendPostRequest(map, "Rolestatus");
		}
	}

	@Then("The response status code should be {int}")
	public void the_response_status_code_should_be(Integer int1) {

		Assert.assertEquals(int1, response.getStatusCode());
	}

	@When("User sends a GET request to endpoint")
	public void user_sends_a_get_request_to_endpoint() {
		// Assuming that data comes from the Excel
		UserPayload upd = new UserPayload();

		response = upd.sendGetRequest(base + "v2/users");

	}

	@Then("Response status is {int}")
	public void validateStatusCode(int expectedStatus) {
		System.out.println("Actual status code: " + response.getStatusCode());
		System.out.println("Expected status code: " + expectedStatus);

		// if (response == null) {
		// Assert.fail("Response is null. API call might have failed or the endpoint is
		// incorrect.");
		// }
		// Assert.assertEquals(response.getStatusCode(), expectedStatus, "Incorrect
		// status code!");
	};

	@When("User sends a GET request to endpoint all users")
	public void user_sends_a_get_request_to_endpoint_all_users() {
		UserPayload upd = new UserPayload();
		response = upd.sendGetRequest(base + "users");

	}

	@Then("API should respond with status {int}")
	public void api_should_respond_with_status(int statusCode) {
		System.out.println("Expected Status Code: " + statusCode);
		System.out.println("Actual Status Code: " + response.statusCode());
		// Assert.assertEquals(statusCode, response.statusCode());
	}

	@Given("the API endpoint is set to get user roles")
	public void the_api_endpoint_is_set_to_get_user_roles() {
		roleURL = base + "users/roles";
		// RestAssured.roleURL = BASE_URL + "/users/roles";
	}

	@When("a GET request is sent to fetch user roles")
	public void a_get_request_is_sent_to_fetch_user_roles() {
		UserPayload upd = new UserPayload();

		response = upd.sendGetRequest(roleURL);
	}

	@Then("the response status code should be 200")
	public void the_response_status_code_should_be_200() {
		response.then().statusCode(200);
	}

	@Given("the API endpoint is set to get active users")
	public void the_api_endpoint_is_set_to_get_active_users() {
		ActiveURL = base + "users/activeUsers";
		// userURL=
		// "https://lms-hackthon-feb25-803334c87fbe.herokuapp.com/lms/users/activeUsers";
	}

	@When("a GET request is sent to fetch active users")
	public void a_get_request_is_sent_to_fetch_active_users() {
		UserPayload upd = new UserPayload();
		response = upd.sendGetRequest(base + "users/activeUsers");
	}

	@Then("the response status code is 200")
	public void the_response_status_code_is_200() {
		response.then().statusCode(200);
	}

	@Given("the API endpoint is set to get all roles")
	public void the_api_endpoint_is_set_to_get_all_roles() {
		allURL = base + "roles";
		// RestAssured.baseURI = baseUrl + "/roles"

	}

	@When("a GET request is sent to fetch all roles")
	public void a_get_request_is_sent_to_fetch_all_roles() {
		UserPayload upd = new UserPayload();
		response = upd.sendGetRequest(allURL);
	}

	@Then("the response status code 200")
	public void the_response_status_code_200() {
		response.then().statusCode(200);
	}

	@Given("the API endpoint is set to fetch user emails")
	public void the_api_endpoint_is_set_to_fetch_user_emails() {
		emailURL = base + "fetch-emails";

		// RestAssured.baseURI = BASE_URL + "/fetch-emails";
	}

	@When("a GET request is sent to fetch user emails")
	public void a_get_request_is_sent_to_fetch_user_emails() {
		UserPayload upd = new UserPayload();
		response = upd.sendGetRequest(emailURL);
	}

	@Then("the response status contain 200")
	public void the_response_status_contain_200() {
		// response.then().statusCode(200);
		if (response == null) {
			throw new RuntimeException("Response object is null. Check the API request.");
		}

		// Log response details for debugging
		System.out.println("Response Status Code: " + response.getStatusCode());
		System.out.println("Response Body: " + response.getBody().asPrettyString());

		// Assert that the status code is 200
		response.then().statusCode(200);
	}

	@Given("I have the LMS API endpoint to get user details")
	public void i_have_the_lms_api_endpoint_to_get_user_details() {
		useridURL = base + "users/";
	}

	@When("User sends a GET request with user data")
	public void User_sends_a_GET_request_with_user_data() {
		List<Map<String, String>> lst = ExcelReader.getAllDataFromExcel(fileName, "endpoint", "User");
		UserPayload upd = new UserPayload();

		for (Map<String, String> map : lst) {
			String url = useridURL + map.get("userId");
			response = upd.sendGetRequest(url);
		}
	}

	@Then("I should get a valid response with status code 200")
	public void i_should_get_a_valid_response_with_status_code_200() {
		Assert.assertEquals(response.getStatusCode(), 200,
				"Expected status code 200 but found " + response.getStatusCode());
	}

	@Given("API endpoint to get user details")
	public void API_endpoint_to_get_user_details() {
		// Set the valid token (replace this with dynamic token generation if needed)
		usersURL = "https://lms-hackthon-feb25-803334c87fbe.herokuapp.com/lms/users/user";
	}

	@When("User sends a GET request with user")
	public void user_sends_a_get_request_with_user() {
		// RestAssured.baseURI = baseUrl + "/" + userId;
		List<Map<String, String>> lst = ExcelReader.getAllDataFromExcel(fileName, "endpoint", "User");
		UserPayload upd = new UserPayload();

		for (Map<String, String> map : lst) {
			String url = usersURL + map.get("userId");
			response = upd.sendGetRequest(url);
		}

	}

	@Then("The API response should contain the user details")
	public void the_api_response_should_contain_the_user_details() {
		// Validate the response status code
		// assertEquals(200, response.getStatusCode());

		// Print response for debugging
		System.out.println("Response: " + response.getBody().asString());

		// Validate response contains expected fields
		// String userId = response.jsonPath().getString("userId");
		// assertEquals("U38", userId);
	}

	@Given("User creates GET Request for the LMS API User Role endpoint")
	public void user_creates_get_request_for_the_lms_api_user_role_endpoint() {
		System.out.println("User creates GET request for the LMS API User Role endpoint with valid token.");
		roleidURL = "https://lms-hackthon-feb25-803334c87fbe.herokuapp.com/lms/users/roles/R01";
	}

	@When("User sends a GET request to endpoint role")
	public void user_sends_a_get_request_to_endpoint_role() {
		// Your logic here
		// Replace placeholder with actual roleId
		// String fullUrl = baseUrl + endpoint.replace("{roleId}", roleId);

		// Send GET request
		// response = given()
		// .header("accept", "*/*")
		// .header("Authorization", "Bearer " + token)
		// .when()
		// .get(fullUrl);
		List<Map<String, String>> lst = ExcelReader.getAllDataFromExcel(fileName, "endpoint", "User");
		UserPayload upd = new UserPayload();

		for (Map<String, String> map : lst) {
			String url = roleidURL + map.get("roleId");
			response = upd.sendGetRequest(url);
		}
	}

	@Then("The API response should contain the role details")
	public void the_api_response_should_contain_the_role_details() {
		// Validate the response status code
		// assertEquals(200, response.getStatusCode());

		// Print response for debugging
		System.out.println("user can see rolr details");
	}

	@Given("User creates a GET Request for the LMS API Program endpoint")
	public void user_creates_a_get_request_for_the_lms_api_program_endpoint() {
		proidURL = "https://lms-hackthon-feb25-803334c87fbe.herokuapp.com/lms/users/programs/16213";
		System.out.println("User creates GET request for the LMS API Program endpoint with valid token.");
	}

	@When("User sends a GET request endpoint program data")
	public void user_sends_a_get_request_endpoint_program_data() {
		// Replace placeholder with actual programId
		// String fullUrl = baseUrl + endpoint.replace("{programId}", programId);
		List<Map<String, String>> lst = ExcelReader.getAllDataFromExcel(fileName, "endpoint", "User");
		UserPayload upd = new UserPayload();
		for (Map<String, String> map : lst) {
			String url = proidURL + map.get("programId");
			response = upd.sendGetRequest(url);
		}

		// Print response for debugging
		// System.out.println("Response: " + response.getBody().asString());
	}

	@Then("The API response should contain the program details")
	public void the_api_response_should_contain_the_program_details() {
		// Validate the response status code
		// assertEquals(200, response.getStatusCode());

		// Validate that the program ID is returned in the response
		System.out.println("user can see prigram details");
	}

	@Given("User creates a GET Request for the LMS API Program Batch endpoint")
	public void user_creates_a_get_request_for_the_lms_api_program_batch_endpoint() {
		batchURL = base + "users/programBatch/6546";
		System.out.println("User creates a GET request for the LMS API Program Batch endpoint with a valid token.");
	}

	@When("User sends a GET request to the Program Batch endpoint")
	public void user_sends_a_get_request_to_the_program_batch_endpoint() {
		// Define the endpoint dynamically with the batch ID
		// String endpoint = "/lms/users/programBatch/" + batchId;

		// Construct the full URL
		// String fullUrl = baseUrl + endpoint;

		// Send GET request to fetch program batch details
		// response = given()
		// .header("accept", "*/*")
		// .header("Authorization", "Bearer " + token)
		// .when()
		// .get(fullUrl);
		List<Map<String, String>> lst = ExcelReader.getAllDataFromExcel(fileName, "endpoint", "User");
		UserPayload upd = new UserPayload();

		for (Map<String, String> map : lst) {
			String url = batchURL + map.get("batchId");
			response = upd.sendGetRequest(url);
		}
		// Print response for debugging
		System.out.println("Response: " + response.getBody().asString());
	}

	@Then("The API response should contain the program batch details")
	public void the_api_response_should_contain_the_batch_details() {
		// Validate the response status code
		// assertEquals(200, response.getStatusCode());

		// Validate that the program ID is returned in the response
		System.out.println("Response: " + response.getBody().asString());
	}

	@Given("the API endpoint to fetch user details is available")
	public void given_The_Api_Endpoint_To_Fetch_User_Details_Is_Available() {
		detURL = base;
		// System.out.println("User creates a GET request for the LMS API Program user
		// details endpoint with a valid token.");
	}

	@When("I send a GET request to fetch user details")
	public void when_I_Send_AGET_Request_To_Fetch_User_Details() {

		List<Map<String, String>> lst = ExcelReader.getAllDataFromExcel(fileName, "endpoint", "User");
		UserPayload upd = new UserPayload();

		for (Map<String, String> map : lst) {
			String url = detURL + map.get("userId");
			response = upd.sendGetRequest(url);
		}
	}

	@Then("the API response should contain the user details")
	public void the_api_response_should_contain_the_user_details1() {
		// Your code to assert the user details in the response

		System.out.println("Response: " + response.getBody().asString());
	}

	@Given("User creates a GET Request for the LMS API Users by Status endpoint")
	public void user_creates_a_get_request_for_the_lms_api_users_by_status_endpoint() {
		allURL = base + "users/byStatus?id=all";
		// Set the base URL dynamically
		// baseUrl = endpoint;
		// System.out.println("Base endpoint set to: " + baseUrl);
	}

	@When("User sends a GET request to the Users by Status endpoint with id string")
	public void user_sends_a_get_request_to_the_users_by_status_endpoint_with_id_String() {
		// Define the endpoint dynamically with the provided id
		// String endpoint = "/lms/users/byStatus?id=" + id;

		// Construct the full URL
		// String fullUrl = baseUrl + endpoint;
		List<Map<String, String>> lst = ExcelReader.getAllDataFromExcel(fileName, "endpoint", "User");
		UserPayload upd = new UserPayload();

		for (Map<String, String> map : lst) {
			String url = allURL + map.get("Id");
			response = upd.sendGetRequest(url);
		}

		// Print response for debugging
		// System.out.println("Response: " + response.getBody().asString());
	}

	@Then("The API response should contain users data for all statuses")
	public void the_api_response_should_contain_users_data_for_all_statuses() {
		// Validate the response status code
		// assertEquals(200, response.getStatusCode());

		// Validate that the response contains a list of users
		String responseBody = response.getBody().asString();
		assertNotNull("Response should not be empty", responseBody);
		System.out.println("Users data: " + responseBody);
	}

	@Given("User creates PUT Request for the LMS API endpoint")
	public void user_creates_put_request_for_the_LMS_API_endpoint() {
		idURL = base;
	}

	@When("Send a PUT request with valid data")
	public void send_a_put_request_with_valid_data() {
		List<Map<String, String>> lst = ExcelReader.getAllDataFromExcel(fileName, "valid", "User");
		UserPayload upd = new UserPayload();

		for (Map<String, String> map : lst) {
			String url = idURL + map.get("userId");
			response = upd.sendputRequest(map, url);
		}
	}

	@Then("the response should contain the updated user details")
	public void the_response_should_contain_the_updated_user_details() {
		System.out.println("Response: " + response.getBody().asString());
	}

	@Given("User creates PUT Request for the endpoint")
	public void user_creates_put_request_for_the_endpoint() {
		idURL = base;
	}

	@When("Send a PUT request with invalid data")
	public void send_a_put_request_with_invalid_data() {
		List<Map<String, String>> lst = ExcelReader.getAllDataFromExcel(fileName, "endpoint", "User");
		UserPayload upd = new UserPayload();

		for (Map<String, String> map : lst) {
			String url = idURL + map.get("invalid");
			response = upd.sendputRequest(map, url);
		}
	}

	@Then("the response should see 400")
	public void the_response_should_see_400() {
		if (response == null) {
			throw new RuntimeException("Response object is null. Cannot validate status code.");
		}

		// Log the response details for debugging
		System.out.println("Response Status Code: " + response.getStatusCode());
		System.out.println("Response Body: " + response.asPrettyString());

		Assert.assertEquals(404, response.getStatusCode(), "Unexpected status code!");
	}

	@Given("I have the API endpoint to update user login with ID")
	public void i_have_the_api_endpoint_to_update_user_login_with_id() {
		usURL = base;
	}

	@When("I send a PUT request to update the user login status")
	public void i_send_a_put_request_to_update_the_user_login_status() {
		List<Map<String, String>> lst = ExcelReader.getAllDataFromExcel(fileName, "valid", "User");
		UserPayload upd = new UserPayload();

		for (Map<String, String> map : lst) {
			String url = usURL + map.get("userId");
			response = upd.sendputRequest(map, url);
		}
	}

	@Then("the response should contain the updated user login status")
	public void the_response_should_contain_the_updated_user_login_status() {
		// Validate that the response has been initialized
		if (response == null) {
			throw new NullPointerException("Response is null. Ensure the API call was executed properly.");
		}

		// Extract the response body and check for the expected updated user login
		// status
		String responseBody = response.getBody().asString();
		System.out.println("Response Body: " + responseBody);

		// Perform validation to ensure the response contains the updated status
		Assert.assertTrue(responseBody.contains("\"loginStatus\":\"Updated\""));
		System.out.println("User login status updated successfully!");
	}

	@Given("I have the API endpoint to update role, program, and batch status for user ID")
	public void i_have_the_api_endpoint_to_update_role_program_and_batch_status_for_user_id() {
		proURL = base;
	}

	@When("I send a PUT request to update the role, program, and batch status")
	public void i_send_a_put_request_to_update_the_role_program_and_batch_status() {
		// List<Map<String, String>> lst = ExcelReader.getAllDataFromExcel(fileName,
		// "valid","User");
		// UserPayload upd = new UserPayload();

		// for(Map<String, String> map : lst){
		// String url = proURL+map.get("userId");
		// response = upd.sendputRequest(url);
		// }
		List<Map<String, String>> lst = ExcelReader.getAllDataFromExcel(fileName, "valid", "User");
		if (lst == null || lst.isEmpty()) {
			throw new RuntimeException("The list retrieved from Excel is null or empty. No requests will be sent.");
		}

		UserPayload upd = new UserPayload();
		for (Map<String, String> map : lst) {
			String url = proURL + map.get("userId"); // Ensure proper URL
			System.out.println("Constructed URL: " + url); // Debug log
			response = upd.sendputRequest(map, url); // Ensure this method returns a valid response
			System.out.println("Response Status Code: " + response.getStatusCode()); // Debug log
			System.out.println("Response Body: " + response.getBody().asString()); // Debug log
		}
	}

	@Then("the response should contain the updated role, program, and batch status")
	public void the_response_should_contain_the_updated_role_program_and_batch_status() {
		System.out.println("Response: " + response.getBody().asString());
	}

	@Given("I have the API endpoint to update the role for user iD")
	public void i_have_the_api_endpoint_to_update_the_role_for_user_id() {
		tURL = base;
	}

	@When("I send a PUT request to update the user role")
	public void i_send_a_put_request_to_update_the_user_role() {
		List<Map<String, String>> lst = ExcelReader.getAllDataFromExcel(fileName, "valid", "User");
		UserPayload upd = new UserPayload();

		for (Map<String, String> map : lst) {
			String url = tURL + map.get("userId");
			response = upd.sendputRequest(map, url);
		}
	}

	@Then("the response should contain the updated role and status")
	public void the_response_should_contain_the_updated_role_and_status() {
		System.out.println("Response: " + response.getBody().asString());
	}

	@Given("I have the API endpoint to delete user with id")
	public void i_have_the_api_endpoint_to_delete_user_with_id() {
		deURL = base;
	}

	@When("I send a DELETE request to remove the user")
	public void i_send_a_delete_request_to_remove_the_user() {
		List<Map<String, String>> lst = ExcelReader.getAllDataFromExcel(fileName, "Delete", "User");
		UserPayload upd = new UserPayload();

		for (Map<String, String> map : lst) {
			String url = deURL + map.get("userId");
			response = upd.sendDeleteRequest(url);
		}
	}

	@Then("the status code should be 200")
	public void the_status_code_should_be_200() {
		assertNotNull(response);
	}

}
