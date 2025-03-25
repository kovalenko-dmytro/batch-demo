package com.gmail.apach.dima.batch_demo.application.core.job.validator;

import com.gmail.apach.dima.batch_demo.common.constant.Error;
import com.gmail.apach.dima.batch_demo.common.util.MessageUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.batch.core.BatchStatus;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JobExecutionValidatorTest {

    private static final String ERROR_PARAM = "The previous job execution is still running";

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
        assertThrows(IllegalStateException.class,
            () -> jobExecutionValidator.checkNotStarted(BatchStatus.STARTED));
        verify(messageUtil, times(1))
            .getMessage(eq(Error.JOB_INTERRUPTED), eq(ERROR_PARAM));
    }
}