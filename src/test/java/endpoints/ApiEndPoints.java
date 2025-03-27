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
	getClassInvalidEndPoint("/allClasse "),
	
	Rolestatus("/users/roleStatus"),
	GetV2users("/v2/users"),
	Getallusers("/users"),
	Getrole("/users/roles"),
	GetActiveusers("/users/activeUsers"),
	updateuser("/users/U54"),
	Deletebyuserid("/users/U89");

	

  
	String endPoint;

	ApiEndPoints(String endPoint) {
		this.endPoint = endPoint;
	}
	public String getEndPoint()
	{
		return endPoint;
	}

}
