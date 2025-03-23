package pojo;

public class BatchPojo {
    private String batchName;
    private String batchDescription;
    private String batchNoOfClasses;
    private String batchStatus;
    private Integer programId;
    private Integer batchId;
    private String programName;

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public String getBatchDescription() {
        return batchDescription;
    }

    public void setBatchDescription(String batchDescription) {
        this.batchDescription = batchDescription;
    }

    public String getBatchNoOfClasses() {
        return batchNoOfClasses;
    }

    public void setBatchNoOfClasses(String batchNoOfClasses) {
        this.batchNoOfClasses = batchNoOfClasses;
    }

    public String getBatchStatus() {
        return batchStatus;
    }

    public void setBatchStatus(String batchStatus) {
        this.batchStatus = batchStatus;
    }

    public Integer getProgramId() {
        return programId;
    }

    public void setProgramId(Integer programId) {
        this.programId = programId;
    }

    public Integer getBatchId() {
        return batchId;
    }

    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }
}
