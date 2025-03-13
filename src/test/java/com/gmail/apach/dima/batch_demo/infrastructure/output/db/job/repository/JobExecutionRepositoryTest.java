package com.gmail.apach.dima.batch_demo.infrastructure.output.db.job.repository;

import com.gmail.apach.dima.batch_demo.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

@Sql({"classpath:sql/get_job.sql"})
class JobExecutionRepositoryTest extends AbstractIntegrationTest {

    private static final String EXEC_UNIQUE_MARK = "f8ec8765-308a-4c26-9543-a84a369ba81f" ;

    @Autowired
    private JobExecutionRepository jobExecutionRepository;

    @Test
    void getJob_exists() {
        final var actual = jobExecutionRepository.get(EXEC_UNIQUE_MARK);

        assertNotNull(actual);
        assertTrue(actual.isPresent());
        assertEquals(EXEC_UNIQUE_MARK, actual.get().getJobExecutionMarker());
    }

    @Test
    void getJob_empty() {
        final var actual = jobExecutionRepository.get("wrong-exec-mark");

        assertNotNull(actual);
        assertTrue(actual.isEmpty());
    }
}