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
	deleteProgramByprogramName("/deletebyprogname/{programName}"),
	programInvalidEndPoint("/saveprogramjava"),
	
	addNewClass("/CreateClassSchedule "),
	getAllClass("/allClasses "),
	getAllUpcomingClassByStudentId("/upcomingClasses/{studentID}"),
	getClassRecordingsByBatchId("/batchRecordings/9477"),
	getClassDetailsById("/class/675"),
	getAllClassByTopic("/classes/pythonSyntax"),
	getAllClassByBatchId("/classesbyBatch/9477"),
	getClassRecordingsByInvalidBatchId("/batchRecordings/947"),
	getClassDetailsByInvalidClassId("/class/67"),
	getClassDetailsByInvalidClassTopic("/class/pythonSyn"),
	getAllClassByInvalidBatchId("/classesbyBatch/947"),
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
