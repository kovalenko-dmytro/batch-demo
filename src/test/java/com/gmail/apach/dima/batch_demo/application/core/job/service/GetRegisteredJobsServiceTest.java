package com.gmail.apach.dima.batch_demo.application.core.job.service;

import com.gmail.apach.dima.batch_demo.application.receiver.RegisteredJobsReceiver;
import com.gmail.apach.dima.batch_demo.port.output.db.job.GetRegisteredJobsOutputPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetRegisteredJobsServiceTest {

    @InjectMocks
    private GetRegisteredJobsService getRegisteredJobsService;
    @Mock
    private GetRegisteredJobsOutputPort getRegisteredJobsOutputPort;

    @Test
    void getRegisteredJobs_success() {
        when(getRegisteredJobsOutputPort.get()).thenReturn(RegisteredJobsReceiver.registeredJobs());

        final var actual = getRegisteredJobsService.get();

        assertNotNull(actual);
        assertFalse(actual.isEmpty());
    }
}