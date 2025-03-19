package com.gmail.apach.dima.batch_demo.infrastructure.output.db.job.registry;

import com.gmail.apach.dima.batch_demo.infrastructure.output.db.job.registry.repository.JobRegistryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RegisteredJobExistsDbServiceTest {

    private static final String JOB_NAME = "some-job";

    @InjectMocks
    private RegisteredJobExistsDbService registeredJobExistsDbService;
    @Mock
    private JobRegistryRepository jobRegistryRepository;

    @Test
    void exist_true() {
        when(jobRegistryRepository.exist(JOB_NAME)).thenReturn(true);
        final var actual = registeredJobExistsDbService.exist(JOB_NAME);
        assertEquals(Boolean.TRUE, actual);
    }

    @Test
    void exist_false() {
        when(jobRegistryRepository.exist(JOB_NAME)).thenReturn(false);
        final var actual = registeredJobExistsDbService.exist(JOB_NAME);
        assertEquals(Boolean.FALSE, actual);
    }
}