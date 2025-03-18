package com.gmail.apach.dima.batch_demo.infrastructure.output.db.job.registry;

import com.gmail.apach.dima.batch_demo.application.receiver.RegisteredJobsReceiver;
import com.gmail.apach.dima.batch_demo.infrastructure.output.db.job.registry.mapper.RegisteredJobMapper;
import com.gmail.apach.dima.batch_demo.infrastructure.output.db.job.registry.repository.JobRegistryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetRegisteredJobsAdapterTest {

    @InjectMocks
    private GetRegisteredJobsAdapter getRegisteredJobsAdapter;
    @Mock
    private JobRegistryRepository jobRegistryRepository;
    @Mock
    private RegisteredJobMapper registeredJobMapper;

    @Test
    void getRegisteredJobs_success() {
        when(jobRegistryRepository.getRegisteredJobs())
            .thenReturn(RegisteredJobsReceiver.registeredJobsNames());
        when(registeredJobMapper.toRegisteredJob(RegisteredJobsReceiver.registeredJobsNames().get(0)))
            .thenReturn(RegisteredJobsReceiver.registeredJobs().get(0));
        when(registeredJobMapper.toRegisteredJob(RegisteredJobsReceiver.registeredJobsNames().get(1)))
            .thenReturn(RegisteredJobsReceiver.registeredJobs().get(1));

        final var actual = getRegisteredJobsAdapter.getAll();

        assertNotNull(actual);
        assertFalse(actual.isEmpty());
    }
}