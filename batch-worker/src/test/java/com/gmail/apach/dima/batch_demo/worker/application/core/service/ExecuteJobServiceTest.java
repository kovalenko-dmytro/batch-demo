package com.gmail.apach.dima.batch_demo.worker.application.core.service;

import com.gmail.apach.dima.batch_demo.common.constant.Error;
import com.gmail.apach.dima.batch_demo.common.constant.Info;
import com.gmail.apach.dima.batch_demo.common.model.RequestParameter;
import com.gmail.apach.dima.batch_demo.common.util.MessageUtil;
import com.gmail.apach.dima.batch_demo.worker.application.receiver.RequestParametersReceiver;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExecuteJobServiceTest {

    @InjectMocks
    private ExecuteJobService executeJobService;
    @Mock
    private ApplicationContext context;
    @Mock
    private JobLauncher jobLauncher;
    @Mock
    private MessageUtil messageUtil;

    @Test
    void execute_jobBeanNotFound() throws Exception {
        final var requestParameters = RequestParametersReceiver.parameters();
        final var jobName = requestParameters.get(RequestParameter.JOB_NAME);

        doThrow(NoSuchBeanDefinitionException.class)
            .when(context).getBean(jobName, Job.class);

        executeJobService.execute(requestParameters);

        verify(context, times(1))
            .getBean(jobName, Job.class);
        verify(jobLauncher, times(0))
            .run(any(Job.class), any(JobParameters.class));
        verify(messageUtil, times(1))
            .getMessage(eq(Error.JOB_FAILED), eq(jobName), any(), any());
    }

    @Test
    void execute_jobReturnExecution() throws Exception {
        final var requestParameters = RequestParametersReceiver.parameters();
        final var jobName = requestParameters.get(RequestParameter.JOB_NAME);
        final var job = mock(Job.class);
        final var jobExecution = mock(JobExecution.class);
        final var batchStatus = BatchStatus.COMPLETED;

        when(context.getBean(jobName, Job.class))
            .thenReturn(job);
        when(jobLauncher.run(any(), any()))
            .thenReturn(jobExecution);
        when(jobExecution.getStatus())
            .thenReturn(batchStatus);

        executeJobService.execute(requestParameters);

        verify(context, times(1))
            .getBean(jobName, Job.class);
        verify(jobLauncher, times(1))
            .run(any(), any());
        verify(messageUtil, times(1))
            .getMessage(eq(Info.JOB_FINISHED), eq(jobName), any(), eq(batchStatus));
    }
}