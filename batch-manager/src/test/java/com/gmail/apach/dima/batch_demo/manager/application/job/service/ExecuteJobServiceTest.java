package com.gmail.apach.dima.batch_demo.manager.application.job.service;

import com.gmail.apach.dima.batch_demo.common.exception.ResourceNotFoundException;
import com.gmail.apach.dima.batch_demo.common.exception.ValidationException;
import com.gmail.apach.dima.batch_demo.common.model.JobExecutionInfo;
import com.gmail.apach.dima.batch_demo.common.model.JobExecutionResult;
import com.gmail.apach.dima.batch_demo.common.model.RequestParameter;
import com.gmail.apach.dima.batch_demo.manager.application.job.validator.JobExecutionValidator;
import com.gmail.apach.dima.batch_demo.manager.application.job.validator.JobRegistrationValidator;
import com.gmail.apach.dima.batch_demo.manager.application.receiver.RequestParametersReceiver;
import com.gmail.apach.dima.batch_demo.manager.port.output.rest.ExecuteJobOutputPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExecuteJobServiceTest {

    private static final String DUPLICATE_MARKER = "5a8d68c8-2f28-4b53-ac5a-2db586512440";

    @InjectMocks
    private ExecuteJobService executeJobService;
    @Mock
    private JobRegistrationValidator jobRegistrationValidator;
    @Mock
    private JobExecutionService jobExecutionService;
    @Mock
    private JobExecutionValidator jobExecutionValidator;
    @Mock
    private ExecuteJobOutputPort executeJobOutputPort;

    @Test
    void execute_jobNotRegistered() {
        final var requestParameters = RequestParametersReceiver.parameters();
        final var jobName = requestParameters.get(RequestParameter.JOB_NAME);

        doThrow(ResourceNotFoundException.class)
            .when(jobRegistrationValidator).checkRegistration(jobName);

        assertThrows(ResourceNotFoundException.class,
            () -> executeJobService.execute(requestParameters));

        verify(jobExecutionService, times(0))
            .getLastJobExecution(any());
        verify(jobExecutionValidator, times(0))
            .checkNotStarted(any());
        verify(jobExecutionService, times(0))
            .getJobExecutionsWithParameters(jobName, requestParameters);
        verify(jobExecutionValidator, times(0))
            .checkUniqueParameters(any(), any());
        verify(executeJobOutputPort, times(0))
            .execute(requestParameters);
    }

    @Test
    void execute_jobHasAlreadyStarted() {
        final var jobExecution = mock(JobExecution.class);
        final var batchStatus = BatchStatus.STARTED;
        final var requestParameters = RequestParametersReceiver.parameters();
        final var jobName = requestParameters.get(RequestParameter.JOB_NAME);

        doNothing()
            .when(jobRegistrationValidator).checkRegistration(jobName);
        when(jobExecutionService.getLastJobExecution(jobName))
            .thenReturn(Optional.of(jobExecution));
        when(jobExecution.getStatus())
            .thenReturn(batchStatus);
        doThrow(ValidationException.class)
            .when(jobExecutionValidator).checkNotStarted(batchStatus);

        assertThrows(ValidationException.class,
            () -> executeJobService.execute(requestParameters));

        verify(jobRegistrationValidator, times(1))
            .checkRegistration(jobName);
        verify(jobExecutionService, times(1))
            .getLastJobExecution(jobName);
        verify(jobExecutionValidator, times(1))
            .checkNotStarted(any());
        verify(jobExecutionService, times(0))
            .getJobExecutionsWithParameters(jobName, requestParameters);
        verify(jobExecutionValidator, times(0))
            .checkUniqueParameters(any(), any());
        verify(executeJobOutputPort, times(0))
            .execute(requestParameters);
    }

    @Test
    void execute_checkUniqueParametersAlreadyExist() {
        final var jobExecution = mock(JobExecution.class);
        final var batchStatus = BatchStatus.COMPLETED;
        final var requestParameters = RequestParametersReceiver.parameters();
        final var jobName = requestParameters.get(RequestParameter.JOB_NAME);
        final var jobParametersBuilder = new JobParametersBuilder();
        jobParametersBuilder.addString(RequestParameter.JOB_EXECUTION_MARKER.getName(), DUPLICATE_MARKER);
        final var jobExecutions = List.of(new JobExecution(1L, jobParametersBuilder.toJobParameters()));

        doNothing()
            .when(jobRegistrationValidator).checkRegistration(jobName);
        when(jobExecutionService.getLastJobExecution(jobName))
            .thenReturn(Optional.of(jobExecution));
        when(jobExecution.getStatus())
            .thenReturn(batchStatus);
        when(jobExecutionService.getJobExecutionsWithParameters(jobName, requestParameters))
            .thenReturn(jobExecutions);
        doThrow(ValidationException.class)
            .when(jobExecutionValidator).checkUniqueParameters(jobExecutions, requestParameters);

        assertThrows(ValidationException.class,
            () -> executeJobService.execute(requestParameters));

        verify(jobRegistrationValidator, times(1))
            .checkRegistration(jobName);
        verify(jobExecutionService, times(1))
            .getLastJobExecution(jobName);
        verify(jobExecutionValidator, times(1))
            .checkNotStarted(any());
        verify(jobExecutionService, times(1))
            .getJobExecutionsWithParameters(jobName, requestParameters);
        verify(jobExecutionValidator, times(1))
            .checkUniqueParameters(jobExecutions, requestParameters);
        verify(executeJobOutputPort, times(0))
            .execute(requestParameters);
    }

    @Test
    void execute_success() {
        final var jobExecution = mock(JobExecution.class);
        final var batchStatus = BatchStatus.COMPLETED;
        final var requestParameters = RequestParametersReceiver.parameters();
        final var jobName = requestParameters.get(RequestParameter.JOB_NAME);
        final var marker = requestParameters.get(RequestParameter.JOB_EXECUTION_MARKER);
        final var jobExecutions = List.of(jobExecution);
        final var workerJobExecution = JobExecutionResult.builder()
            .info(JobExecutionInfo.builder()
                .jobName(jobName)
                .jobExecutionMarker(marker)
                .batchStatus(batchStatus.name())
                .build())
            .build();

        doNothing()
            .when(jobRegistrationValidator).checkRegistration(jobName);
        when(jobExecutionService.getLastJobExecution(jobName))
            .thenReturn(Optional.of(jobExecution));
        when(jobExecution.getStatus())
            .thenReturn(batchStatus);
        when(jobExecutionService.getJobExecutionsWithParameters(jobName, requestParameters))
            .thenReturn(jobExecutions);
        doNothing()
            .when(jobExecutionValidator).checkUniqueParameters(jobExecutions, requestParameters);
        when(executeJobOutputPort.execute(requestParameters))
            .thenReturn(workerJobExecution);

        final var actual = executeJobService.execute(requestParameters);
        assertNotNull(actual);
        assertNotNull(actual.info());
        assertEquals(jobName, actual.info().jobName());
        assertEquals(marker, actual.info().jobExecutionMarker());
        assertEquals(batchStatus.name(), actual.info().batchStatus());

        verify(jobRegistrationValidator, times(1))
            .checkRegistration(jobName);
        verify(jobExecutionService, times(1))
            .getLastJobExecution(jobName);
        verify(jobExecutionValidator, times(1))
            .checkNotStarted(any());
        verify(jobExecutionService, times(1))
            .getJobExecutionsWithParameters(jobName, requestParameters);
        verify(jobExecutionValidator, times(1))
            .checkUniqueParameters(jobExecutions, requestParameters);
        verify(executeJobOutputPort, times(1))
            .execute(requestParameters);
    }
}