package payload;

import static io.restassured.RestAssured.given;

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
import pojo.ProgramPojo;
import utils.CommonUtils;
import utils.ConfigReader;
import utils.ExcelReader;

public class ProgramPayload extends CommonUtils {
	RequestSpecification request;
	ResponseSpecification resspec;
	public Response response;
	public String loginToken;
	public String programId;

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
			;

			request = given().spec(requestSpecification())
					.body(addNewProgram(programDescription, programName, programStatus))
					.header("Authorization", "Bearer " + loginToken);
		}
	}

	public void requestWithInvalidEndpoint() {
		ApiEndPoints addProgramEndpoint = ApiEndPoints.valueOf("programInvalidEndPoint");
		response = request.when().post(addProgramEndpoint.getEndPoint());

	}

	public void ProgramResponse(String method, String endPoint) {
		ApiEndPoints addProgramEndpoint = ApiEndPoints.valueOf(endPoint);

		if (method.equalsIgnoreCase("POST")) {
			response = request.when().post(addProgramEndpoint.getEndPoint());
			programId = response.jsonPath().getString("programId");
		} else if (method.equalsIgnoreCase("PUT")) {
			response = request.when().put(addProgramEndpoint.getEndPoint());
			programId = response.jsonPath().getString("programId");
		} else if (method.equalsIgnoreCase("GET"))
			response = request.when().get(addProgramEndpoint.getEndPoint());
		else if (method.equalsIgnoreCase("DELETE"))
			response = request.when().delete(addProgramEndpoint.getEndPoint() + "/" + programId);

	}

	public void createProgramWithInvalidRequestBody() throws IOException {
		List<Map<String, String>> list = ExcelReader.getAllDataFromExcel("programBook.xlsx", "Invalid",
				"programModule");
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

	public void createProgramWithMissingValueInAdditionalField() throws IOException {
		List<Map<String, String>> list = ExcelReader.getAllDataFromExcel("programBook.xlsx",
				"MissingValueInAdditionalField", "programModule");
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
}
