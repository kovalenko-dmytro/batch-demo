package com.gmail.apach.dima.batch_demo.application.batch.import_csv_to_db.job;

import com.gmail.apach.dima.batch_demo.AbstractIntegrationTest;
import com.gmail.apach.dima.batch_demo.application.core.job.registry.JobRegistry;
import com.gmail.apach.dima.batch_demo.application.receiver.JobParametersReceiver;
import com.gmail.apach.dima.batch_demo.infrastructure.adapter.output.db.master.repository.MasterTableRepository;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class ImportCsvToDbJobTest extends AbstractIntegrationTest {

    private static final String FILE_PATH = "src/test/resources/file/import_csv_to_db/csv_to_db_test.csv";

    @Autowired
    private MasterTableRepository masterTableRepository;

    @Test
    void execute_success() throws Exception {
        final var storedResource = uploadFile(FILE_PATH);
        final var jobParameters = JobParametersReceiver.importCsvToD(storedResource.getStorageKey());
        final var execution = launchJob(JobRegistry.IMPORT_CSV_TO_DB, jobParameters);

        assertEquals(JobRegistry.IMPORT_CSV_TO_DB, execution.getJobInstance().getJobName());
        assertEquals(ExitStatus.COMPLETED, execution.getExitStatus());

        final var rows = masterTableRepository.findAll();

        assertNotNull(rows);
        assertFalse(rows.isEmpty());
        assertEquals(15, rows.size());
    }
}