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
