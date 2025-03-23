package payload;

import pojo.BatchPojo;

public class BatchPayload {
    public BatchPojo createBatch(String batchName, String batchDescription, String batchNoOfClasses,
                                 String batchStatus, Integer programId, Integer batchId, String programName) {

        BatchPojo batchPojo = new BatchPojo();
        batchPojo.setBatchName(batchName);
        batchPojo.setBatchDescription(batchDescription);
        batchPojo.setBatchNoOfClasses(batchNoOfClasses);
        batchPojo.setBatchStatus(batchStatus);
        batchPojo.setProgramId(programId);
        batchPojo.setBatchId(batchId);
        batchPojo.setProgramName(programName);

        return batchPojo;
    }
}
