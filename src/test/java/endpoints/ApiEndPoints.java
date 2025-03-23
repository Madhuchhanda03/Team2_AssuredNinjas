package endpoints;

public enum ApiEndPoints {

	userLogin("/login"),
	addNewProgram("/saveprogram"),
	getAllPrograms("/allPrograms"),
	getAllProgramWithUsers("/allProgramsWithUsers"),
	getProgramByProgramId("/programs/{programId}"),
	updateprogramByprogramName("/program/{programName}"),
	updateprogramByprogramId("/putprogram/{programId}"),
	deleteProgramByprogramId("/deletebyprogid/{programId}"),
	deleteProgramByprogramName("/deletebyprogname/{programName}");
	//**********BATCH MODULE ENDPOINTS**********
	public enum endpoint	{
		CREATEBATCH("/batches"),
		CreateBatch_invalidEndpoint("/batchesss"),
		GETALLBATCHES("/batches"),
		GetAllBatches_InvalidEndpoint("/batchesss"),
		GETBATCHBYBATCHID("/batches/batchId/"),
		GETBATCHBYBATCHNAME("/batches/batchName/"),
		GETBATCHBYPROGRAMID("/batches/program/"),
		UPDATEBYBATCHID("/batches/"),
		DELETEBYBATCHID("/batches/");

		private final String path;

		endpoint(String path) {
			this.path=path;
		}

		public String getPath() {
			return path;
		}

	}

	String endPoint;

	ApiEndPoints(String endPoint) {
		this.endPoint = endPoint;
	}
	public String getEndPoint()
	{
		return endPoint;
	}

}
