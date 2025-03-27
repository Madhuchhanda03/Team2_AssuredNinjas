package payload;
import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.Arrays;
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
import pojo.ClassPojo;
import utils.CommonUtils;
import utils.ConfigReader;
import utils.ExcelReader;
public class ClassPayload extends CommonUtils{
	RequestSpecification request;
	ResponseSpecification resspec;
	public Response response;
	public String loginToken;
	public String classId;
	public ClassPojo createclass(int batchId, int classNo, String classDate, String classTopic, String classStaffId, String classDescription, String classComments, String classNotes, String classRecordingPath, List<String> classScheduledDates ) {
		ClassPojo classPojo = new ClassPojo();
		classPojo.setBatchId(batchId);
		classPojo.setClassNo(classNo);
		classPojo.setClassDate(classDate);
		classPojo.setClassTopic(classTopic);
		classPojo.setClassStaffId(classStaffId);
		classPojo.setClassDescription(classDescription);
		classPojo.setClassComments(classComments);
		classPojo.setClassNotes(classNotes);
		classPojo.setClassRecordingPath(classRecordingPath);
		classPojo.setClassScheduledDates(classScheduledDates);
		
		
		
		return classPojo;

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
	public void createClassPayload() throws IOException {
		List<Map<String, String>> list = ExcelReader.getAllDataFromExcel("programBook.xlsx", "Valid" , "classModule");
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Map<String, String> map = (Map<String, String>) iterator.next();
			
            String batchId1 = map.get("batchId");
            int batchId=Integer.parseInt(batchId1);
            String classNo1 = map.get("classNo");
			int classNo=Integer.parseInt(classNo1);
            String classDate = map.get("classDate");
			String classTopic  = map.get("classTopic");
			String classStaffId = map.get("classStaffId");
			String classDescription = map.get("classDescription");
			String classComments = map.get("classComments");
			String classNotes = map.get("classNotes");
			String classRecordingPath = map.get("classRecordingPath");
			String classscheduledDatesString = map.get("classscheduledDates");
			 List<String> classScheduledDates = Arrays.asList(classscheduledDatesString.split(","));

			request = given().spec(requestSpecification())
					.body(createclass(batchId, classNo, classDate, classTopic, classStaffId, classDescription, classComments, classNotes, classRecordingPath, classScheduledDates))
					.header("Authorization", "Bearer " + loginToken);
}
	}
	public void requestWithInvalidEndpoint() {
		ApiEndPoints moduleEndpoint = ApiEndPoints.valueOf("classInvalidEndPoint");
		response = request.when().post(moduleEndpoint.getEndPoint());

	}
	public void ClassResponse(String method, String endPoint) {
		ApiEndPoints moduleEndpoint = ApiEndPoints.valueOf(endPoint);

		if (method.equalsIgnoreCase("POST")) {
			response = request.when().post(moduleEndpoint.getEndPoint());
	classId = response.jsonPath().getString("classId");
		} else if (method.equalsIgnoreCase("PUT")) {
			response = request.when().put(moduleEndpoint.getEndPoint());
			classId = response.jsonPath().getString("classId");
		} else if (method.equalsIgnoreCase("GET"))
			response = request.when().get(moduleEndpoint.getEndPoint());
		else if (method.equalsIgnoreCase("DELETE"))
			response = request.when().delete(moduleEndpoint.getEndPoint() + "/" + classId);

	}

}


