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
	programInvalidEndPoint("/saveprogramws"),
    CREATEBATCH("/batches"),
    CreateBatch_invalidEndpoint("/batch99esss"),
    GETALLBATCHES("/batches"),
    GetAllBatches_InvalidEndpoint("/batcheshhss"),
    GETBATCHBYBATCHID("/batches/batchId/"),
    GETBATCHBYBATCHNAME("/batches/batchName/"),
    GETBATCHBYPROGRAMID("/batches/program/"),
    UPDATEBYBATCHID("/batches/"),
    DELETEBYBATCHID("/batches/"),

  	//addNewClass("/CreateClassSchedule "),
	getAllClass("/allClasses "),
	getAllUpcomingClassByStudentId("/upcomingClasses/{studentID}"),
	getClassRecordingsByBatchId("/batchRecordings/9477"),
	getClassDetailsById("/class/675"),
	getAllClassByTopic("/classes/pythonSyntax"),
	getAllClassByBatchId("/classesbyBatch/9477"),
	getAllClassByStaffId("/classesByStaff/U61"),
	getAllRecordings("/classrecordings"),
	getAllClassRecordingsByClassId("/classRecordings/57"),

	getAllClassRecordingsByInvalidClassId("/classRecordings/571000000"),
	getClassRecordingsByInvalidBatchId("/batchRecordings/947"),
	getClassDetailsByInvalidClassId("/class/67"),
	getClassDetailsByInvalidClassTopic("/class/pythonSyn"),
	getAllClassByInvalidBatchId("/classesbyBatch/947"),
	getAllClassByInvalidStaffId("/classesByStaff/A61"),
	getClassInvalidEndPoint("/allClasse ");

	String endPoint;

	ApiEndPoints(String endPoint) {
		this.endPoint = endPoint;
	}
	public String getEndPoint()
	{
		return endPoint;
	}

}
