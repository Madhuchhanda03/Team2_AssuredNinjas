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
	programInvalidEndPoint("/saveprogramws"),
	Rolestatus("/users/roleStatus"),
	V2users("/v2/users"),
	allusers("/users"),
	role("/users/roles"),
	Activeusers("/users/activeUsers"),
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
