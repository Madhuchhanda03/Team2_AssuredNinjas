package pojo;

public class CommonIdHolder {
	public static String programId;
	public static String programName;

	public static String getProgramId() {
		return programId;
	}

	public static void setProgramId(String programId) {
		CommonIdHolder.programId = programId;
	}

	public static String getProgramName() {
		return programName;
	}

	public static void setProgramName(String programName) {
		CommonIdHolder.programName = programName;
	}
}
