package payload;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import com.fasterxml.jackson.databind.ObjectMapper;

import endpoints.ApiEndPoints;
import hooks.Hook;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojo.User;
import pojo.User.UserLogin;
import utils.CommonUtils;
import pojo.User.Role;

public class UserPayload extends CommonUtils{
		RequestSpecification request;
		ResponseSpecification resspec;
		public Response response;
		public static String geToken;
		public static int statusCode;

	public Response sendPostRequest(Map<String, String> map, String roleURL) {
		UserLogin ul = new UserLogin(map.get("userLoginEmail"), map.get("loginStatus"), map.get("status"));
		Role rl = new Role(map.get("roleId"), map.get("userRoleStatus"));
		User usr = new User();

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
	public Response V2usersGetRequest(String string) throws IOException {
		ApiEndPoints endPoint = ApiEndPoints.valueOf("V2users");
		try {
			return RestAssured.given()
			        .spec(requestSpecification()) // Use the request specification
			        .contentType(ContentType.JSON) // Set content type to JSON
			        .header("Authorization", "Bearer " + LoginPayload.geToken())
			        .when()
			        .post(endPoint.getEndPoint());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // Make the POST req
		return response;
	}
	public Response allusersGetRequest(String string) throws IOException {
		ApiEndPoints endPoint = ApiEndPoints.valueOf("allusers");
		try {
			return RestAssured.given()
			        .spec(requestSpecification()) // Use the request specification
			        .contentType(ContentType.JSON) // Set content type to JSON
			        //.body(usr) // Attach the payload as the body
			        .header("Authorization", "Bearer " + LoginPayload.geToken())
			        .when()
			        .post(endPoint.getEndPoint());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // Make the POST req
		return response;
	}
	public Response sendGetRequestGetrole( String roleURL) {
		return RestAssured.given().header("Authorization", "Bearer " + LoginPayload.geToken()).contentType(ContentType.JSON)
				.when().get(roleURL);
	}
	public Response sendGetRequestGetActiveUsers ( String ActiveURL) {
		return RestAssured.given().header("Authorization", "Bearer " + LoginPayload.geToken()).contentType(ContentType.JSON)
				.when().get(ActiveURL);
	}
	public Response sendGetRequestGetRoles ( String allURL) {
		return RestAssured.given().header("Authorization", "Bearer " + LoginPayload.geToken()).contentType(ContentType.JSON)
				.when().get(allURL);
	}
	public Response sendGetRequestGetemail( String emailURL) {
		return RestAssured.given().header("Authorization", "Bearer " + LoginPayload.geToken()).contentType(ContentType.JSON)
				.when().get(emailURL);
	}
	public Response sendGetRequestetuserid( String usersURL) {
		return RestAssured.given().header("Authorization", "Bearer " + LoginPayload.geToken()).contentType(ContentType.JSON)
				.when().get(usersURL);
	}
	public Response sendGetRequestGetUserDetails( String useridURL) {
		return RestAssured.given().header("Authorization", "Bearer " + LoginPayload.geToken()).contentType(ContentType.JSON)
				.when().get(useridURL);
	}
	
	public Response sendGetRequestGetRoleDetails( String roleidURL) {
		return RestAssured.given().header("Authorization", "Bearer" + LoginPayload.geToken()).contentType(ContentType.JSON)
				.when().get(roleidURL);
	}
	public Response sendGetRequestGetProgramDetails( String proidURL) {
		return RestAssured.given().header("Authorization", "Bearer " + LoginPayload.geToken()).contentType(ContentType.JSON)
				.when().get(proidURL);
	}
	public Response sendGetRequestGetProgramBatch( String batchURL) {
		return RestAssured.given().header("Authorization", "Bearer " + LoginPayload.geToken()).contentType(ContentType.JSON)
				.when().get(batchURL);
	}
	public Response sendGetRequestGetuserdetails( String detURL) {
		return RestAssured.given().header("Authorization", "Bearer " + LoginPayload.geToken()).contentType(ContentType.JSON)
				.when().get(detURL);
	}

	public Response sendPUTRequestupdatevaliduser(String emailURL) {
		String usr = null;
		return RestAssured.given().header("Authorization", "Bearer " + LoginPayload.geToken()).contentType(ContentType.JSON)
				.body(usr).when().post(emailURL);
	}
	public Response sendPUTRequestupdateinvaliduser(String emailURL) {
		String usr = null;
		return RestAssured.given().header("Authorization", "Bearer " + LoginPayload.geToken()).contentType(ContentType.JSON)
				.body(usr).when().post(emailURL);
	}
	public Response sendPUTRequestUpdateUserLoginStatus(String emailURL) {
		String usr = null;
		return RestAssured.given().header("Authorization", "Bearer " + LoginPayload.geToken()).contentType(ContentType.JSON)
				.body(usr).when().post(emailURL);
	}
	public Response sendPUTRequestUpdateRoleProgramBatch(String emailURL) {
		String usr = null;
		return RestAssured.given().header("Authorization", "Bearer " + LoginPayload.geToken()).contentType(ContentType.JSON)
				.body(usr).when().post(emailURL);
	}
	public Response sendPUTRequestUpdateUserRole(String emailURL) {
		String usr = null;
		return RestAssured.given().header("Authorization", "Bearer " + LoginPayload.geToken()).contentType(ContentType.JSON)
				.body(usr).when().post(emailURL);
	}


	public Response sendDeleteRequest(String url) {
		// TODO Auto-generated method stub
		return null;
	}


	public Response sendputRequest(String url) {
		// TODO Auto-generated method stub
		return null;
	}


	public Response sendGetRequest(String url) {
		// TODO Auto-generated method stub
		return null;
	}
}

