package payload;

import pojo.ProgramPojo;

public class ProgramPayload {
	public ProgramPojo addNewProgram(String programDescription,String programName,String programStatus) {
		ProgramPojo programPojo = new ProgramPojo();
		programPojo.setProgramDescription(programDescription);
		programPojo.setProgramName(programName);
		programPojo.setProgramStatus(programStatus);
		return programPojo;


	}
}
