package com.gmail.apach.dima.batch_demo.application.core.job.validator;

import com.gmail.apach.dima.batch_demo.application.core.job.model.RequestParameter;
import com.gmail.apach.dima.batch_demo.common.constant.Error;
import com.gmail.apach.dima.batch_demo.common.util.MessageUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class FileResourceValidatorTest {

    @InjectMocks
    private FileResourceValidator fileResourceValidator;
    @Mock
    private MessageUtil messageUtil;

    @Test
    void validate_success() {
        final var jobParametersBuilder = new JobParametersBuilder();
        jobParametersBuilder.addString(RequestParameter.FILE_STORAGE_RESOURCE.getName(), "resource");
        assertDoesNotThrow(() -> fileResourceValidator.validate(jobParametersBuilder.toJobParameters()));
    }

    @Test
    void validate_fail() {
        assertThrows(JobParametersInvalidException.class,
            () -> fileResourceValidator.validate(new JobParametersBuilder().toJobParameters()));
        verify(messageUtil, times(1))
            .getMessage(eq(Error.FILE_STORAGE_RESOURCE_MISSING), any());
    }
}