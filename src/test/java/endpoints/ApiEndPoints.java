package endpoints;

public enum ApiEndPoints {

	userLogin("/login"),
	addNewProgram("/saveprogram"),
	getAllPrograms("/allPrograms"),
	getAllProgramWithUsers("/allProgramsWithUsers"),
	getProgramByProgramId("/programs/"),
	updateprogramByprogramName("/program/"),
	updateprogramByprogramId("/putprogram/"),
	deleteProgramByprogramId("/deletebyprogid/"),
	deleteProgramByprogramName("/deletebyprogname/"),
	programInvalidEndPoint("/saveprogramws")
    CREATEBATCH("/batches"),
    CreateBatch_invalidEndpoint("/batchesss"),
    GETALLBATCHES("/batches"),
    GetAllBatches_InvalidEndpoint("/batchesss"),
    GETBATCHBYBATCHID("/batches/batchId/"),
    GETBATCHBYBATCHNAME("/batches/batchName/"),
    GETBATCHBYPROGRAMID("/batches/program/"),
    UPDATEBYBATCHID("/batches/"),
    DELETEBYBATCHID("/batches/");

	String endPoint;

	ApiEndPoints(String endPoint) {
		this.endPoint = endPoint;
	}
	public String getEndPoint()
	{
		return endPoint;
	}

}
