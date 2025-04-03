package com.gmail.apach.dima.batch_demo.manager.application.job.validator;

import com.gmail.apach.dima.batch_demo.common.constant.Error;
import com.gmail.apach.dima.batch_demo.common.exception.ValidationException;
import com.gmail.apach.dima.batch_demo.common.model.RequestParameter;
import com.gmail.apach.dima.batch_demo.common.util.MessageUtil;
import com.gmail.apach.dima.batch_demo.manager.application.receiver.RequestParametersReceiver;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JobExecutionValidatorTest {

    private static final String ERROR_PARAM = "The previous job execution is still running";
    private static final String DUPLICATE_MARKER = "5a8d68c8-2f28-4b53-ac5a-2db586512440";
    private static final String NOT_DUPLICATE_MARKER = "5a8d68c8-2f28-4b53-ac5a-2db586512455";

    @InjectMocks
    private JobExecutionValidator jobExecutionValidator;
    @Mock
    private MessageUtil messageUtil;

    @Test
    void checkNotStarted_success() {
        assertDoesNotThrow(() -> jobExecutionValidator.checkNotStarted(BatchStatus.COMPLETED));
    }

    @Test
    void checkNotStarted_fail() {
        assertThrows(ValidationException.class,
            () -> jobExecutionValidator.checkNotStarted(BatchStatus.STARTED));
        verify(messageUtil, times(1))
            .getMessage(eq(Error.JOB_INTERRUPTED), eq(ERROR_PARAM));
    }

    @Test
    void checkUniqueParameters_success() {
        var emptyJobExecutions = new ArrayList<JobExecution>();
        final var requestParameters = RequestParametersReceiver.parameters();
        assertDoesNotThrow(() -> jobExecutionValidator.checkUniqueParameters(emptyJobExecutions, requestParameters));

        final var jobParametersBuilder = new JobParametersBuilder();
        jobParametersBuilder.addString(RequestParameter.JOB_EXECUTION_MARKER.getName(), NOT_DUPLICATE_MARKER);

        final var jobExecutions = List.of(new JobExecution(1L, jobParametersBuilder.toJobParameters()));
        assertDoesNotThrow(() -> jobExecutionValidator.checkUniqueParameters(jobExecutions, requestParameters));
    }

    @Test
    void checkUniqueParameters_fail() {
        final var requestParameters = RequestParametersReceiver.parameters();
        final var jobParametersBuilder = new JobParametersBuilder();
        jobParametersBuilder.addString(RequestParameter.JOB_EXECUTION_MARKER.getName(), DUPLICATE_MARKER);
        final var jobExecutions = List.of(new JobExecution(1L, jobParametersBuilder.toJobParameters()));

        assertThrows(ValidationException.class,
            () -> jobExecutionValidator.checkUniqueParameters(jobExecutions, requestParameters));
        verify(messageUtil, times(1))
            .getMessage(eq(Error.JOB_EXECUTION_ALREADY_EXISTS), eq(DUPLICATE_MARKER));
    }
}