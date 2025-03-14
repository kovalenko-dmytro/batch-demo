package com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.job;

import com.gmail.apach.dima.batch_demo.AbstractIntegrationTest;
import com.gmail.apach.dima.batch_demo.application.core.job.constant.JobName;
import com.gmail.apach.dima.batch_demo.application.receiver.JobParametersReceiver;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.ExitStatus;

import static org.junit.jupiter.api.Assertions.*;

class ImportXmlZipToExcelJobTest extends AbstractIntegrationTest {

    private static final String FILE_PATH =
        "src/test/resources/file/import_xml_zip_to_excel/import_xml_zip_to_excel.zip";

    @Test
    void execute_success() throws Exception {
        final var storedResource = uploadFile(FILE_PATH);
        final var jobParameters = JobParametersReceiver.importXmlZipToExcel(storedResource.getStorageKey());
        final var execution = launchJob(JobName.IMPORT_XML_ZIP_TO_EXCEL, jobParameters);

        assertEquals(JobName.IMPORT_XML_ZIP_TO_EXCEL, execution.getJobInstance().getJobName());
        assertEquals(ExitStatus.COMPLETED, execution.getExitStatus());

        final var exportedExcelFile = awsS3Adapter.get("export_file.xlsx");

        assertNotNull(exportedExcelFile);
        assertTrue(exportedExcelFile.getPayload().length > 0);
    }
}