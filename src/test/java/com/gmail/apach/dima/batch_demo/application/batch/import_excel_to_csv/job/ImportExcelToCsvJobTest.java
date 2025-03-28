package com.gmail.apach.dima.batch_demo.application.batch.import_excel_to_csv.job;

import com.gmail.apach.dima.batch_demo.AbstractIntegrationTest;
import com.gmail.apach.dima.batch_demo.application.core.job.constant.JobName;
import com.gmail.apach.dima.batch_demo.application.receiver.JobParametersReceiver;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.ExitStatus;

import static org.junit.jupiter.api.Assertions.*;

class ImportExcelToCsvJobTest extends AbstractIntegrationTest {

    private static final String FILE_PATH = "src/test/resources/file/import_excel_to_csv/excel_to_csv_test.xlsx";

    @Test
    void execute_success() throws Exception {
        final var storedResource = uploadFile(FILE_PATH);
        final var jobParameters = JobParametersReceiver.importExcelToCsv(storedResource.getStorageKey());
        final var execution = launchJob(JobName.IMPORT_EXCEL_TO_CSV, jobParameters);

        assertEquals(JobName.IMPORT_EXCEL_TO_CSV, execution.getJobInstance().getJobName());
        assertEquals(ExitStatus.COMPLETED, execution.getExitStatus());

        final var exportedCsvFile = awsS3Service.get("excel_to_csv_test.csv");

        assertNotNull(exportedCsvFile);
        assertTrue(exportedCsvFile.getPayload().length > 0);
    }
}