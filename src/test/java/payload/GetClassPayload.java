package payload;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import endpoints.ApiEndPoints;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import utils.CommonUtils;

public class GetClassPayload extends CommonUtils {
	RequestSpecification request;
	ResponseSpecification resspec;
	public Response response;
	//public String loginToken;
	public String classId;
	//ProgramPayload programPlayload;
	public void ClassGetAllRequest() throws IOException {
		//programPlayload = new ProgramPayload();
		request = given().spec(requestSpecification())
				.header("Authorization", "Bearer " + ProgramPayload.loginToken );
	}
	
	public void ClassGetAllRequestWithoutAuthorization() throws IOException {
		request = given().spec(requestSpecification());
		
	}
	
	public void ClassgetRequestWithInvalidEndpoint() {
		ApiEndPoints moduleEndpoint = ApiEndPoints.valueOf("getClassInvalidEndPoint");
		response = request.when().get(moduleEndpoint.getEndPoint());

	}
	
	public void ClassResponse(String method, String endPoint) {
		ApiEndPoints moduleEndpoint = ApiEndPoints.valueOf(endPoint);

		if (method.equalsIgnoreCase("POST")) {
			response = request.when().post(moduleEndpoint.getEndPoint());
			classId = response.jsonPath().getString("csId");
		} else if (method.equalsIgnoreCase("PUT")) {
			response = request.when().put(moduleEndpoint.getEndPoint());
			classId = response.jsonPath().getString("csId");
		} else if (method.equalsIgnoreCase("GET"))
			response = request.when().get(moduleEndpoint.getEndPoint());
		else if (method.equalsIgnoreCase("DELETE"))
			response = request.when().delete(moduleEndpoint.getEndPoint() + "/" + classId);

	}


}
