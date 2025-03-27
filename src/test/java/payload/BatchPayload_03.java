package payload;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import endpoints.ApiEndPoints;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.BatchPojo_03;
import pojo.CommonIdHolder;
import pojo.ProgramPojo;
import utils.CommonUtils;
import utils.ExcelReader;

public class BatchPayload_03 extends CommonUtils {
	RequestSpecification request;
	ResponseSpecification resspec;
	public static Response response;
	public static String loginToken;
	CommonIdHolder commonIdHolder;
	
	public BatchPojo_03 addNewBatch(String batchDescription, String batchName,int batchNoOfClasses, String batchStatus,int id) {
		BatchPojo_03 batchPojo = new BatchPojo_03();
		batchPojo.setBatchDescription(batchDescription);
		batchPojo.setBatchName(batchName);
		batchPojo.setBatchNoOfClasses(batchNoOfClasses);
		batchPojo.setBatchStatus(batchStatus);
		batchPojo.setProgramId(id);
		return batchPojo;

	}

	public void createBatchPayload() throws IOException {
		commonIdHolder=new CommonIdHolder();
		String proId=commonIdHolder.getProgramId();
		int newProgramId=Integer.parseInt(proId);
		
		List<Map<String, String>> list = ExcelReader.getAllDataFromExcel("programBook.xlsx", "valid", "BatchModule");
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Map<String, String> map = (Map<String, String>) iterator.next();
//System.out.println("****PRINT STAT****"+list);
			String batchDescription = map.get("batchDescription");
			System.out.println("****PRINT STAT****"+batchDescription);
			String batchName = map.get("batchName");
			String batchNoOfClasses=map.get("batchNoOfClasses");
			int noOfClass=Integer.parseInt(batchNoOfClasses);
			String batchStatus = map.get("batchStatus");
			

			request = given().spec(requestSpecification())
					.body(addNewBatch(batchDescription, batchName,noOfClass ,batchStatus,newProgramId))
					.header("Authorization", "Bearer " + loginToken);
		}
		
	}
	public void batchPostHttpRequest(String method, String endPoint) {

		ApiEndPoints moduleEndpoint = ApiEndPoints.valueOf(endPoint);
		response = request.when().post(moduleEndpoint.getEndPoint());
		
		
	}
}
