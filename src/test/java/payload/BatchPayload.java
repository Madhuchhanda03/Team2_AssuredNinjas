
package payload;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import endpoints.ApiEndPoints;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.BatchPojo;
import pojo.CommonIdHolder;
import utils.CommonUtils;
import utils.ConfigReader;
import utils.ExcelReader;
import utils.LoggerLoad;

public class BatchPayload extends CommonUtils {
    public BatchPojo createdBatchPojo; //
    RequestSpecification request;
    ResponseSpecification resspec;
    public Response response;
    public String loginToken;
    public String batchId;
    public CommonIdHolder programPayload = new CommonIdHolder();

    public BatchPojo addNewBatch(String batchDescription, String batchName, String batchNoOfClasses,
                                 String batchStatus, Integer programId, String programName) {
        BatchPojo batchPojo = new BatchPojo();
        batchPojo.setBatchDescription(batchDescription);
        batchPojo.setBatchName(batchName);
        batchPojo.setBatchNoOfClasses(batchNoOfClasses);
        batchPojo.setBatchStatus(batchStatus);
        batchPojo.setProgramId(programId);
        batchPojo.setProgramName(programName);
        return batchPojo;
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

    public void createBatchFromExcel(String sheetName) throws IOException {
        //programPayload = new CommonIdHolder();
        //programPayload.createNewProgramPayload();
        List<Map<String, String>> list = ExcelReader.getAllDataFromExcel("programBook.xlsx", "BatchModule", "BatchPostdata");
        for (Map<String, String> map : list) {
            String batchDescription = map.get("BatchDescription");
            String batchName = map.get("BatchName");
            String batchNoOfClasses = map.get("NoOfClasses");
            String batchStatus = map.get("BatchStatus");
//            Integer programId = Integer.parseInt(map.get("ProgramId"));
//            String programName = map.get("ProgramName");

            // Use both programId and programName from ProgramPayload
            LoggerLoad.info("*********** Program ID: " + programPayload.programId);
            Integer programId = Integer.parseInt(programPayload.programId);  // not static
            String programName = programPayload.programName;
            request = given()
                    .spec(requestSpecification())
                    .body(addNewBatch(batchDescription, batchName, batchNoOfClasses, batchStatus, programId, programName))
                    .header("Authorization", "Bearer " + loginToken);
            // Execute POST request using the correct enum endpoint
            response = request.when().post(ApiEndPoints.CREATEBATCH.getEndPoint());

            // Optionally store the batchId if needed later
            batchId = response.jsonPath().getString("batchId");
        }
    }

    public void requestWithInvalidEndpoint() {
        ApiEndPoints endpoint = ApiEndPoints.CreateBatch_invalidEndpoint;
        response = request.when().post(endpoint.getEndPoint());
    }

    public void createBatchWithExistingBatchName() throws IOException {
       // programPayload = new CommonIdHolder();
       // programPayload.createNewProgramPayload();

        List<Map<String, String>> list = ExcelReader.getAllDataFromExcel("programBook.xlsx", "Existing Batch Info", "BatchPostdata");

        for (Map<String, String> map : list) {
            if ("Existing Batch Info".equalsIgnoreCase(map.get("Scenario").trim())) {

                String batchDescription = map.get("BatchDescription");
                String batchName = map.get("BatchName"); // duplicate name
                String batchNoOfClasses = map.get("NoOfClasses");
                String batchStatus = map.get("BatchStatus");

                Integer programId = Integer.parseInt(programPayload.programId);
                String programName = programPayload.programName;

                request = given()
                        .spec(requestSpecification())
                        .header("Authorization", "Bearer " + loginToken)
                        .body(addNewBatch(batchDescription, batchName, batchNoOfClasses, batchStatus, programId, programName));

                response = request.when().post(ApiEndPoints.CREATEBATCH.getEndPoint());

                // Log and verify status (expected 400 or duplicate error)
                System.out.println("Attempted to create batch with existing name. Status: " + response.getStatusCode());
                break; // only handle one row
            }
        }
    }



    public void batchResponse(String method, ApiEndPoints batchEndpoint) {
        if (method.equalsIgnoreCase("POST")) {
            response = request.when().post(batchEndpoint.getEndPoint());
            batchId = response.jsonPath().getString("batchId");
        } else if (method.equalsIgnoreCase("PUT")) {
            response = request.when().put(batchEndpoint.getEndPoint());
            batchId = response.jsonPath().getString("batchId");
        } else if (method.equalsIgnoreCase("GET")) {
            response = request.when().get(batchEndpoint.getEndPoint());
        } else if (method.equalsIgnoreCase("DELETE")) {
            response = request.when().delete(batchEndpoint.getEndPoint() + "/" + batchId);
        }
    }

    public void createBatchWithMissingMandatoryFields() throws IOException {
        List<Map<String, String>> list = ExcelReader.getAllDataFromExcel("programBook.xlsx", "Missing mandatory fields", "BatchPostdata");

        for (Map<String, String> map : list) {
            if ("Missing mandatory fields".equalsIgnoreCase(map.get("Scenario").trim())) {
                String batchDescription = map.get("BatchDescription");
                String batchName = map.get("BatchName");
                String batchNoOfClasses = map.get("NoOfClasses");
                String batchStatus = map.get("BatchStatus");
                String programIdStr = map.get("ProgramId");
                String programName = map.get("ProgramName");

                Integer programId = null;
                try {
                    programId = Integer.parseInt(programIdStr);
                } catch (NumberFormatException e) {
                    // skip invalid numeric conversion
                }

                BatchPojo batchPojo = addNewBatch(batchDescription, batchName, batchNoOfClasses, batchStatus, programId, programName);

                request = given()
                        .spec(requestSpecification())
                        .header("Authorization", "Bearer " + loginToken)
                        .body(batchPojo);
                break;


            }
        }

    }

    //Invalid Data

    public void createBatchWithInvalidDataFormat() throws IOException {


        List<Map<String, String>> list = ExcelReader.getAllDataFromExcel("programBook.xlsx", "Invalid NoOfClasses Data", "BatchPostdata");

        for (Map<String, String> map : list) {
            if ("Invalid NoOfClasses Data".equalsIgnoreCase(map.get("Scenario").trim())) {
                String batchDescription = map.get("BatchDescription");
                String batchName = map.get("BatchName");
                String batchNoOfClasses = map.get("NoOfClasses");  // contains invalid chars
                String batchStatus = map.get("BatchStatus");

                Integer programId = Integer.parseInt(programPayload.programId);
                String programName = programPayload.programName;

                BatchPojo invalidPojo = addNewBatch(batchDescription, batchName, batchNoOfClasses, batchStatus, programId, programName);

                request = given()
                        .spec(requestSpecification())
                        .header("Authorization", "Bearer " + loginToken)
                        .body(invalidPojo);
                break;
            }
        }
    }



    //**********SCHEMA VALIDATION*************
    public void validateBatchSchema() {
        response.then().assertThat()
                .body(matchesJsonSchemaInClasspath("schema/batch_schema.json"));
    }

    //Validate ErrorSchema
    public void validateErrorSchema() {
        response.then().assertThat()
                .body(matchesJsonSchemaInClasspath("schema/error_schema.json"));
    }


    //******GET REQUEST*****

    // Get all batches with valid endpoint
    public void getAllBatches() throws IOException {
        request = given().spec(requestSpecification())
                .header("Authorization", "Bearer " + loginToken);

        response = request.when().get(ApiEndPoints.GETALLBATCHES.getEndPoint());
    }

    // Get all batches with invalid endpoint
    public void getAllBatchesInvalidEndpoint() throws IOException {
        request = given().spec(requestSpecification())
                .header("Authorization", "Bearer " + loginToken);

        response = request.when().get(ApiEndPoints.GetAllBatches_InvalidEndpoint.getEndPoint());
    }

    // Get all batches WITHOUT Authorization
    public void getAllBatchesWithoutAuth() throws IOException {
        request = given().spec(requestSpecification()); // No token header added
        response = request.when().get(ApiEndPoints.GETALLBATCHES.getEndPoint());
    }
    //***********GET BATCH BY BATCHID**************


    public void getBatchById() throws IOException {
        response = given().spec(requestSpecification())
                .header("Authorization", "Bearer " + loginToken)
                .when()
                .get(ApiEndPoints.GETBATCHBYBATCHID.getEndPoint() + batchId);


        // Schema validation (optional, if you have batch_batch_schema.json in resources folder)
        response.then().assertThat()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("../batch_schema.json"));
    }

    public void getBatchByName() throws IOException {
        String batchName = createdBatchPojo.getBatchName(); // fetched from created batch

        response = given()
                .spec(requestSpecification())
                .header("Authorization", "Bearer " + loginToken)
                .pathParam("batchName", batchName)
                .when()
                .get(ApiEndPoints.GETBATCHBYBATCHNAME.getEndPoint() + "{batchName}");

        // Simple status check
        if (response.statusCode() == 200) {
            System.out.println("Retrieved batch by name: " + batchName);
        } else {
            throw new RuntimeException("Failed to retrieve batch by name. Status: " + response.statusCode());
        }

        // Optional: validate schema
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("../batch_schema.json"));
    }


    public void getBatchByProgramId() throws IOException {
        Integer programId = createdBatchPojo.getProgramId(); // set during creation
        response = given()
                .spec(requestSpecification())
                .header("Authorization", "Bearer " + loginToken)
                .pathParam("programId", programId)
                .when()
                .get(ApiEndPoints.GETBATCHBYPROGRAMID.getEndPoint() + "{programId}");

        // Logging
        if (response.statusCode() == 200) {
            System.out.println("✅ Successfully retrieved batches by Program ID: " + programId);
        } else {
            throw new RuntimeException("Failed to retrieve by Program ID. Status code: " + response.statusCode());
        }

        // Optional: validate schema
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("batch_schema.json"));
    }

    // Invalid GET with empty value (like empty batch name)
    public void getBatchWithEmptyPathParam(String type) throws IOException {
        request = given().spec(requestSpecification())
                .header("Authorization", "Bearer " + loginToken);

        switch (type.toLowerCase()) {
            case "batchname":
                response = request.when().get(ApiEndPoints.GETBATCHBYBATCHNAME.getEndPoint());
                break;
            case "programid":
                response = request.when().get(ApiEndPoints.GETBATCHBYPROGRAMID.getEndPoint());
                break;
            case "batchid":
                response = request.when().get(ApiEndPoints.GETBATCHBYBATCHID.getEndPoint());
                break;
            default:
                throw new IllegalArgumentException("Invalid GET type provided: " + type);
        }
    }
    //*************INVALID HTTP METHOD***************
    public void sendInvalidHttpMethod(String endpointType, String method, String paramValue) throws IOException {
        request = given()
                .spec(requestSpecification())
                .header("Authorization", "Bearer " + loginToken);

        String endpoint;
        switch (endpointType.toLowerCase()) {
            case "getbybatchid":
                endpoint = ApiEndPoints.GETBATCHBYBATCHID.getEndPoint() + paramValue;
                break;
            case "getbybatchname":
                endpoint = ApiEndPoints.GETBATCHBYBATCHNAME.getEndPoint() + paramValue;
                break;
            case "getbyprogramid":
                endpoint = ApiEndPoints.GETBATCHBYPROGRAMID.getEndPoint() + paramValue;
                break;
            default:
                throw new IllegalArgumentException("Invalid endpoint type: " + endpointType);
        }

        switch (method.toUpperCase()) {
            case "POST":
                response = request.when().post(endpoint);
                break;
            case "PUT":
                response = request.when().put(endpoint);
                break;
            case "DELETE":
                response = request.when().delete(endpoint);
                break;
            case "PATCH":
                response = request.when().patch(endpoint);
                break;
            default:
                throw new IllegalArgumentException("Invalid HTTP method: " + method);
        }

        System.out.println("🔁 Sent " + method + " to " + endpoint + " | Status: " + response.getStatusCode());
    }


    //*********method to handle GET requests with empty/missing parameters******************
    public void sendGetRequestWithEmptyParam(String endpointType, String method, String paramValue) throws IOException {
        request = given()
                .spec(requestSpecification())
                .header("Authorization", "Bearer " + loginToken);

        String endpoint;
        switch (endpointType.toLowerCase()) {
            case "getbybatchid":
                endpoint = ApiEndPoints.GETBATCHBYBATCHID.getEndPoint() + paramValue;
                break;
            case "getbybatchname":
                endpoint = ApiEndPoints.GETBATCHBYBATCHNAME.getEndPoint() + paramValue;
                break;
            case "getbyprogramid":
                endpoint = ApiEndPoints.GETBATCHBYPROGRAMID.getEndPoint() + paramValue;
                break;
            default:
                throw new IllegalArgumentException("Invalid endpoint type: " + endpointType);
        }

        switch (method.toUpperCase()) {
            case "GET":
                response = request.when().get(endpoint);
                break;
            default:
                throw new IllegalArgumentException("Only GET allowed for this scenario.");
        }

        System.out.println("Sent GET to " + endpoint + " | Status: " + response.getStatusCode());
    }


    //***********UPDATE BATCH BY BATCHID**************

    public void updateBatchById() throws IOException {
        if (createdBatchPojo == null || batchId == null) {
            throw new IllegalStateException("No batch available to update. Please create a batch first.");
        }

        // Read updated values from Excel
        List<Map<String, String>> list = ExcelReader.getAllDataFromExcel(
                "programBook.xlsx", "Update valid batch", "BatchPostData");

        for (Map<String, String> map : list) {
            if ("Update valid batch".equalsIgnoreCase(map.get("Scenario").trim())) {
                // Use updated values from Excel
                String updatedBatchName = map.get("BatchName") + "_Updated";
                String updatedNoOfClasses = map.get("NoOfClasses");

                // Keep existing values
                String batchDescription = createdBatchPojo.getBatchDescription();
                String batchStatus = createdBatchPojo.getBatchStatus();
                Integer programId = createdBatchPojo.getProgramId();
                String programName = createdBatchPojo.getProgramName();

                // Create updated payload
                BatchPojo updatedBatch = addNewBatch(
                        batchDescription,
                        updatedBatchName,
                        updatedNoOfClasses,
                        batchStatus,
                        programId,
                        programName
                );

                request = given()
                        .spec(requestSpecification())
                        .header("Authorization", "Bearer " + loginToken)
                        .body(updatedBatch)
                        .pathParam("batchId", batchId);

                response = request.when().put(ApiEndPoints.UPDATEBYBATCHID.getEndPoint() + "{batchId}");

                // Schema validation
                validateBatchSchema();

                System.out.println("Batch updated with ID: " + batchId + ", New Name: " + updatedBatchName);
            }
        }


    }

}

