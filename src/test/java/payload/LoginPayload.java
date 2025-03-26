package payload;
import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import endpoints.ApiEndPoints;
import pojo.LoginRequest;
import pojo.LoginResponse;
import utils.CommonUtils;
import utils.ExcelReader;
public class LoginPayload extends CommonUtils {
	  private LoginResponse loginResponse = null;
	  private static String fileName = "team2Data.xlsx";


public Response sendPostRequest(String url, LoginRequest payload) throws IOException {
    // Fetch the endpoint based on the enum value
    ApiEndPoints endPoint = ApiEndPoints.valueOf("userLogin");

    // Construct and send the POST request
    return RestAssured.given()
            .spec(requestSpecification()) // Use the request specification
            .contentType(ContentType.JSON) // Set content type to JSON
            .body(payload) // Attach the payload as the body
            .when()
            .post(endPoint.getEndPoint()); // Make the POST request to the endpoint
}

public LoginResponse login(String scenario) {
	
	String token = null;

    // Write code here that turns the phrase above into concrete actions
	List<Map<String, String>> lst = ExcelReader.getAllDataFromExcel(fileName, scenario,"Login");
	for (Iterator iterator = lst.iterator(); iterator.hasNext();) {
		Map<String, String> map = (Map<String, String>) iterator.next();
		LoginRequest loginRequest  = new LoginRequest(map.get("userLoginEmailId"), map.get("password"));

         LoginPayload lp = new LoginPayload();
          Response response;
		try {
			response = lp.sendPostRequest("https://lms-hackthon-feb25-803334c87fbe.herokuapp.com/lms/login", loginRequest);

	           loginResponse = new LoginResponse();
	          loginResponse.setMessage(response.asPrettyString());
	          token = response.jsonPath().getString("token");
	         loginResponse.setStatusCode(response.getStatusCode());
	         loginResponse.setToken(token);
	         System.out.println("Authorization Token: " + token);
	         token = response.jsonPath().getString("token");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
  return loginResponse;
}

static Response response = null;
public static String geToken() {
	List<Map<String, String>> lst = ExcelReader.getAllDataFromExcel(fileName, "auth","Auth");

	for (Iterator iterator = lst.iterator(); iterator.hasNext();) {
		Map<String, String> map = (Map<String, String>) iterator.next();
		LoginRequest loginRequest  = new LoginRequest(map.get("user"), map.get("password"));

         LoginPayload lp = new LoginPayload();
           try {
			response = lp.sendPostRequest("https://lms-hackthon-feb25-803334c87fbe.herokuapp.com/lms/login", loginRequest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         break;
		
	}
	return response.jsonPath().getString("token");
}
  
}



	

	
