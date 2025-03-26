package endpoints;

public enum ApiEndPoints {

	userLogin("/login"),
	addNewProgram("/saveprogram"),
	getAllPrograms("/allPrograms"),
	getAllProgramWithUsers("/allProgramsWithUsers"),
	getProgramByProgramId("/programs/"),
	updateprogramByprogramName("/program/{programName}"),
	updateprogramByprogramId("/putprogram/{programId}"),
	deleteProgramByprogramId("/deletebyprogid/{programId}"),
	deleteProgramByprogramName("/deletebyprogname/{programName}"),
    CREATEBATCH("/batches"),
    CreateBatch_invalidEndpoint("/batchesss"),
    GETALLBATCHES("/batches"),
    GetAllBatches_InvalidEndpoint("/batchesss"),
    GETBATCHBYBATCHID("/batches/batchId/"),
    GETBATCHBYBATCHNAME("/batches/batchName/"),
    GETBATCHBYPROGRAMID("/batches/program/"),
    UPDATEBYBATCHID("/batches/"),
    DELETEBYBATCHID("/batches/");

	programInvalidEndPoint("/saveprogramws");

    String endPoint;

	ApiEndPoints(String endPoint) {
		this.endPoint = endPoint;
	}
	public String getEndPoint()
	{
		return endPoint;
	}



    }
