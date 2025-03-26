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
