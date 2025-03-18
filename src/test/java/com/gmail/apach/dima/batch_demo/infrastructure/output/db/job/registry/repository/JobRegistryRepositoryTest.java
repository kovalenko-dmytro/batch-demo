package com.gmail.apach.dima.batch_demo.infrastructure.output.db.job.registry.repository;

import com.gmail.apach.dima.batch_demo.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

@Sql({"classpath:sql/job_registry.sql"})
class JobRegistryRepositoryTest extends AbstractIntegrationTest {

    private static final String JOB_NAME = "job";
    private static final String NOT_REGISTERED_JOB_NAME = "not-registered-job";

    @Autowired
    private JobRegistryRepository jobRegistryRepository;

    @Test
    void getRegisteredJobs_success() {
        final var actual = jobRegistryRepository.getRegisteredJobs();

        assertNotNull(actual);
        assertFalse(actual.isEmpty());
        assertTrue(actual.contains(JOB_NAME));
    }

    @Test
    void exist_true() {
        final var actual = jobRegistryRepository.exist(JOB_NAME);
        assertEquals(Boolean.TRUE, actual);
    }

    @Test
    void exist_false() {
        final var actual = jobRegistryRepository.exist(NOT_REGISTERED_JOB_NAME);
        assertEquals(Boolean.FALSE, actual);
    }
}