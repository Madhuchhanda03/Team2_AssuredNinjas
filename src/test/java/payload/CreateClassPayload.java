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
import pojo.CreateClassPojo;
//import pojo.ProgramPojo;
import utils.CommonUtils;
import utils.ConfigReader;
import utils.ExcelReader;

public class CreateClassPayload extends CommonUtils {
	RequestSpecification request;
	ResponseSpecification resspec;
	public Response response;
	public static String loginToken;
	public String classId;
	
	public CreateClassPojo addNewClass(int batchId,int classNo,String classDate,String classTopic,
			String classStatus,String classNotes,String classDescription, String classComments,
			String classRecordingPath,String classStaffId,List<String> classScheduledDates) {
		
	CreateClassPojo classPojo = new CreateClassPojo();
	
	    classPojo.setBatchId(batchId);
	    classPojo.setClassNo(classNo);
	    classPojo.setClassDate(classDate);
	    classPojo.setClassTopic (classTopic);
	    classPojo.setClassStatus(classStatus);
	    classPojo.setClassNotes(classNotes);
	    classPojo.setClassDescription(classDescription);
	    classPojo.setClassComments(classComments);
	    classPojo.setClassRecordingPath(classRecordingPath);
	    classPojo.setClassStaffId(classStaffId);
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
	public void createNewClassPayload() throws IOException {
		List<Map<String, String>> list = ExcelReader.getAllDataFromExcel("programBook.xlsx", "Valid", "classModule");
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Map<String, String> map = (Map<String, String>) iterator.next();

			String batchId1 = map.get("batchId");
			int batchId=Integer.parseInt(batchId1);
			String classNo1 = map.get("classNo");
			int classNo=Integer.parseInt(classNo1);
			String classDate = map.get("classDate");
			String classTopic =map.get("classTopic");
            String classStatus =map.get("classStatus");
            String classNotes=map.get("classNotes");
            String classDescription=map.get("classDescription");
            String classComments=map.get("classComments");
            String classRecordingPath=map.get("classRecordingPath");
            String classStaffId=map.get("classStaffId");
            String classScheduledDateString=map.get("classScheduledDates");
            List<String> classScheduledDates = Arrays.asList(classScheduledDateString.split(","));

            
		  request = given().spec(requestSpecification())
				.body(addNewClass(batchId,classNo,classDate,classTopic,
						classStatus,classNotes,classDescription, classComments,
						classRecordingPath,classStaffId,classScheduledDates))
					.header("Authorization", "Bearer " + loginToken);
		}
	}
	
	public void ProgramResponse(String method, String endPoint) {
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
