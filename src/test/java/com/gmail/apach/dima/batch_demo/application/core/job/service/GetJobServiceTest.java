package com.gmail.apach.dima.batch_demo.application.core.job.service;

import com.gmail.apach.dima.batch_demo.application.core.job.model.ExecutedJob;
import com.gmail.apach.dima.batch_demo.infrastructure.common.exception.ResourceNotFoundException;
import com.gmail.apach.dima.batch_demo.port.output.db.GetJobOutputPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetJobServiceTest {

    private static final String EXEC_UNIQUE_MARK = "5a8d68c8-2f28-4b53-ac5a-2db586512440" ;

    @InjectMocks
    private GetJobService getJobService;
    @Mock
    private GetJobOutputPort getJobOutputPort;

    @Test
    void getJob_success() {
        final var executedJob = ExecutedJob.builder()
            .jobExecutionMarker(EXEC_UNIQUE_MARK)
            .build();

        when(getJobOutputPort.get(EXEC_UNIQUE_MARK)).thenReturn(executedJob);

        final var actual = getJobService.get(EXEC_UNIQUE_MARK);

        assertNotNull(actual);
        assertEquals(EXEC_UNIQUE_MARK, actual.getJobExecutionMarker());
    }

    @Test
    void getJob_fail() {
        doThrow(new ResourceNotFoundException("notFound")).when(getJobOutputPort).get(EXEC_UNIQUE_MARK);

        assertThrows(ResourceNotFoundException.class, () -> getJobService.get(EXEC_UNIQUE_MARK));
    }
}