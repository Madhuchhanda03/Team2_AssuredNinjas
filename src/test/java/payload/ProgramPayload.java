package payload;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import endpoints.ApiEndPoints;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.CommonIdHolder;
import pojo.ProgramPojo;
import utils.CommonUtils;
import utils.ConfigReader;
import utils.ExcelReader;

public class ProgramPayload extends CommonUtils {
	public String programName;
	RequestSpecification request;
	ResponseSpecification resspec;
	public Response response;
	public static String loginToken;
	public static int statusCode;

	public ProgramPojo addNewProgram(String programDescription, String programName, String programStatus) {
		ProgramPojo programPojo = new ProgramPojo();
		programPojo.setProgramDescription(programDescription);
		programPojo.setProgramName(programName);
		programPojo.setProgramStatus(programStatus);
		return programPojo;

	}

	public void getLoginToken() throws IOException {
		HashMap<String, String> map = new HashMap<>();
		map.put("userLoginEmailId", ConfigReader.readDataFromConfig("userLoginEmailId"));
		map.put("password", ConfigReader.readDataFromConfig("password"));
		ApiEndPoints endPoint = ApiEndPoints.valueOf("userLogin");
		request = given().spec(requestSpecification()).body(map);
		resspec = new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();
		response = request.when().post(endPoint.getEndPoint());
		loginToken = response.jsonPath().getString("token");

	}

	public void createNewProgramPayload() throws IOException {
		List<Map<String, String>> list = ExcelReader.getAllDataFromExcel("programBook.xlsx", "Valid", "programModule");
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Map<String, String> map = (Map<String, String>) iterator.next();

			String programDescription = map.get("programDescription");
			String programName = map.get("programName");
			String programStatus = map.get("programStatus");

			request = given().spec(requestSpecification())
					.body(addNewProgram(programDescription, programName, programStatus))
					.header("Authorization", "Bearer " + loginToken);
		}
	}

	public void requestWithInvalidEndpoint() {
		ApiEndPoints moduleEndpoint = ApiEndPoints.valueOf("programInvalidEndPoint");
		response = request.when().post(moduleEndpoint.getEndPoint());

	}

	public void validPostHttpRequest(String method, String endPoint) {

		ApiEndPoints moduleEndpoint = ApiEndPoints.valueOf(endPoint);
		response = request.when().post(moduleEndpoint.getEndPoint());
		String id = response.jsonPath().getString("programId");
		CommonIdHolder.setProgramId(id);
	}

	public void ProgramResponse(String method, String endPoint) {
		ApiEndPoints moduleEndpoint = ApiEndPoints.valueOf(endPoint);

		if (method.equalsIgnoreCase("POST")) {
			response = request.when().post(moduleEndpoint.getEndPoint());

		} else if (method.equalsIgnoreCase("PUT")) {
			response = request.when().put(moduleEndpoint.getEndPoint());

		} else if (method.equalsIgnoreCase("GET"))
			response = request.when().get(moduleEndpoint.getEndPoint());
		else if (method.equalsIgnoreCase("DELETE"))
			response = request.when().delete(moduleEndpoint.getEndPoint());

	}

	public void getResponseWithProgramId(String method, String endPoint) {
		ApiEndPoints moduleEndpoint = ApiEndPoints.valueOf(endPoint);
		response = request.when().get(moduleEndpoint.getEndPoint() + CommonIdHolder.getProgramId());
	}

	public void createProgramWithInvalidRequestBody() throws IOException {
		List<Map<String, String>> list = ExcelReader.getAllDataFromExcel("programBook.xlsx", "Invalid",
				"programModule");
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Map<String, String> map = (Map<String, String>) iterator.next();
			String programDescription = map.get("programDescription");
			String programName = map.get("programName");
			String programStatus = map.get("programStatus");

			request = given().spec(requestSpecification())
					.body(addNewProgram(programDescription, programName, programStatus))
					.header("Authorization", "Bearer " + loginToken);

		}
	}

	public void createProgramWithMissingValueInAdditionalField() throws IOException {
		List<Map<String, String>> list = ExcelReader.getAllDataFromExcel("programBook.xlsx",
				"MissingValueInAdditionalField", "programModule");
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Map<String, String> map = (Map<String, String>) iterator.next();
			String programDescription = map.get("programDescription");
			String programName = map.get("programName");
			String programStatus = map.get("programStatus");

			request = given().spec(requestSpecification())
					.body(addNewProgram(programDescription, programName, programStatus))
					.header("Authorization", "Bearer " + loginToken);

		}

	}

	public void programWithMissingValue() throws IOException {
		List<Map<String, String>> list = ExcelReader.getAllDataFromExcel("programBook.xlsx",
				"MissingValueInRequestBody", "programModule");
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Map<String, String> map = (Map<String, String>) iterator.next();
			String programDescription = map.get("programDescription");
			String programName = map.get("programName");
			String programStatus = map.get("programStatus");
			;

			request = given().spec(requestSpecification())
					.body(addNewProgram(programDescription, programName, programStatus))
					.header("Authorization", "Bearer " + loginToken);

		}

	}

	// **************************GET REQUEST************************
	public void programGetRequest() throws IOException {
		request = given().spec(requestSpecification()).header("Authorization", "Bearer " + loginToken);
	}

	public void getAllProgramWithoutAuthorization() throws IOException {
		request = given().spec(requestSpecification());

	}

	public void getRequestWithInvalidEndpoint() {
		ApiEndPoints moduleEndpoint = ApiEndPoints.valueOf("programInvalidEndPoint");
		response = request.when().get(moduleEndpoint.getEndPoint());

	}
	// *******************GET REQUEST WITH PROGRAM ID *******************

	public void getRequestWithInvalidProgramId(String endPoint) {
		String invalidProgramId = "5432 11";
		ApiEndPoints moduleEndpoint = ApiEndPoints.valueOf(endPoint);
		response = request.when().get(moduleEndpoint.getEndPoint() + invalidProgramId);
	}

	public void withInvalidBaseUri() throws FileNotFoundException {

		Response getResponse = given().baseUri("https://lms-hackthon-feb25-fbe.herokuapp.com")
				.contentType(ContentType.JSON).header("Authorization", "Bearer " + loginToken).when().get("/programs/");
		statusCode = getResponse.getStatusCode();

	}
}
