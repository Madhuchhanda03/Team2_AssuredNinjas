package payload;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import com.fasterxml.jackson.databind.ObjectMapper;

import endpoints.ApiEndPoints;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojo.UserPojo;
import pojo.UserPojo.UserLogin;
import utils.CommonUtils;
import pojo.UserPojo.Role;

public class UserPayload extends CommonUtils{
		RequestSpecification request;
		ResponseSpecification resspec;
		public Response response;
		public static String geToken;
		public static int statusCode;

	public Response sendPostRequest(Map<String, String> map, String roleURL) {
		UserLogin ul = new UserLogin(map.get("userLoginEmail"), map.get("loginStatus"), map.get("status"));
		Role rl = new Role(map.get("roleId"), map.get("userRoleStatus"));
		UserPojo usr = new UserPojo();

		usr.setUserLogin(ul); // setting user login

		List<Role> lstROle = new ArrayList<Role>();
		lstROle.add(rl);
		usr.setUserRoleMaps(lstROle);
		usr.setUserComments(map.get("userComments"));
		usr.setUserEduPg(map.get("userEduPg"));
		usr.setUserEduUg(map.get("userEduUg"));
		usr.setUserFirstName(map.get("userFirstName"));
		usr.setUserId(map.get("userId"));
		usr.setUserLastName(map.get("userLastName"));
		usr.setUserLinkedinUrl(map.get("userLinkedinUrl"));
		usr.setUserLocation(map.get("userLocation"));
		usr.setUserMiddleName(map.get("userMiddleName"));
		usr.setUserPhoneNumber("+" + map.get("userPhoneNumber"));
		usr.setUserTimeZone(map.get("userTimeZone"));
		usr.setUserVisaStatus(map.get("userVisaStatus"));

		try {
			ObjectMapper objectMapper = new ObjectMapper();
			String json = objectMapper.writeValueAsString(usr);

		} catch (Exception e) {
			e.printStackTrace();
		}
		ApiEndPoints endPoint = ApiEndPoints.valueOf("Rolestatus");
		try {
			return RestAssured.given()
			        .spec(requestSpecification()) // Use the request specification
			        .contentType(ContentType.JSON) // Set content type to JSON
			        .body(usr) // Attach the payload as the body
			        .header("Authorization", "Bearer " + LoginPayload.geToken())
			        .when()
			        .post(endPoint.getEndPoint());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // Make the POST request to the endpoint
		return response;

		//return RestAssured.given().spec(requestSpecification())header("Authorization", "Bearer " + LoginPayload.geToken()).contentType(ContentType.JSON)
				//.body(usr).when().post(roleURL);
	}

	


	public Response sendPUTRequestupdatevaliduser(String idURL) {
		String usr = null;
		return RestAssured.given().header("Authorization", "Bearer " + LoginPayload.geToken()).contentType(ContentType.JSON)
				.body(usr).when().post(idURL);
	}
	public Response sendPUTRequestupdateinvaliduser(String idURL) {
		String usr = null;
		return RestAssured.given().header("Authorization", "Bearer " + LoginPayload.geToken()).contentType(ContentType.JSON)
				.body(usr).when().post(idURL);
	}
	public Response sendPUTRequestUpdateUserLoginStatus(String usURL) {
		String usr = null;
		return RestAssured.given().header("Authorization", "Bearer " + LoginPayload.geToken()).contentType(ContentType.JSON)
				.body(usr).when().post(usURL);
	}
	public Response sendPUTRequestUpdateRoleProgramBatch(String proURL) {
		String usr = null;
		return RestAssured.given().header("Authorization", "Bearer " + LoginPayload.geToken()).contentType(ContentType.JSON)
				.body(usr).when().post(proURL);
	}
	public Response sendPUTRequestUpdateUserRole(String tURL) {
		String usr = null;
		return RestAssured.given().header("Authorization", "Bearer " + LoginPayload.geToken()).contentType(ContentType.JSON)
				.body(usr).when().post(tURL);
	}


	public Response sendDeleteRequest(String url) {
		// TODO Auto-generated method stub
		response = RestAssured.given().header("Authorization", "Bearer " + LoginPayload.geToken()).contentType(ContentType.JSON)
				.when().delete(url);
				return response;
	}


	public Response sendputRequest(Map<String, String> map,String url) {
		UserPojo usr = new UserPojo();


		List<Role> lstROle = new ArrayList<Role>();
		
		usr.setUserComments(map.get("userComments"));
		usr.setUserEduPg(map.get("userEduPg"));
		usr.setUserEduUg(map.get("userEduUg"));
		usr.setUserFirstName(map.get("userFirstName"));
		usr.setUserId(map.get("userId"));
		usr.setUserLastName(map.get("userLastName"));
		usr.setUserLinkedinUrl(map.get("userLinkedinUrl"));
		usr.setUserLocation(map.get("userLocation"));
		usr.setUserMiddleName(map.get("userMiddleName"));
		usr.setUserPhoneNumber("+" + map.get("userPhoneNumber"));
		usr.setUserTimeZone(map.get("userTimeZone"));
		usr.setUserVisaStatus(map.get("userVisaStatus"));
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			String json = objectMapper.writeValueAsString(usr);

		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			return RestAssured.given()
			        .spec(requestSpecification()) // Use the request specification
			        .contentType(ContentType.JSON) // Set content type to JSON
			        .body(usr) // Attach the payload as the body
			        .header("Authorization", "Bearer " + LoginPayload.geToken())
			        .when()
			        .put(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // Make the POST request to the endpoint
		return response;
	}


	public Response sendGetRequest(String url) {
		response = RestAssured.given().header("Authorization", "Bearer " + LoginPayload.geToken()).contentType(ContentType.JSON)
		.when().get(url);
		System.out.print(url);
		return response;
	}
}