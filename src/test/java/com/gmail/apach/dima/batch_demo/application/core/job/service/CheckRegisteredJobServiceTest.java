package com.gmail.apach.dima.batch_demo.application.core.job.service;

import com.gmail.apach.dima.batch_demo.common.constant.Error;
import com.gmail.apach.dima.batch_demo.common.constant.Resource;
import com.gmail.apach.dima.batch_demo.common.exception.ResourceNotFoundException;
import com.gmail.apach.dima.batch_demo.common.util.MessageUtil;
import com.gmail.apach.dima.batch_demo.port.output.db.job.RegisteredJobExistsOutputPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CheckRegisteredJobServiceTest {

    private static final String JOB_NAME = "some-job";

    @InjectMocks
    private CheckJobRegistrationService checkRegisteredJobService;
    @Mock
    private RegisteredJobExistsOutputPort registeredJobExistsOutputPort;
    @Mock
    private MessageUtil messageUtil;

    @Test
    void checkRegistration_success() {
        when(registeredJobExistsOutputPort.exist(JOB_NAME)).thenReturn(true);

        assertDoesNotThrow(() -> checkRegisteredJobService.check(JOB_NAME));
        verify(messageUtil, times(0))
            .getMessage(Error.JOB_NOT_REGISTERED, Resource.Attribute.NAME.getName(), JOB_NAME);
    }

    @Test
    void checkRegistration_fail() {
        when(registeredJobExistsOutputPort.exist(JOB_NAME)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> checkRegisteredJobService.check(JOB_NAME));
        verify(messageUtil, times(1))
            .getMessage(Error.JOB_NOT_REGISTERED, Resource.Attribute.NAME.getName(), JOB_NAME);
    }
}