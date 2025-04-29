package com.gmail.apach.dima.batch_demo.manager.infrastructure.output.rest;

import com.gmail.apach.dima.batch_demo.common.dto.WorkerJobExecutionRequest;
import com.gmail.apach.dima.batch_demo.common.model.JobExecutionInfo;
import com.gmail.apach.dima.batch_demo.common.model.JobExecutionResult;
import com.gmail.apach.dima.batch_demo.common.model.RequestParameter;
import com.gmail.apach.dima.batch_demo.common.model.RestClientErrors;
import com.gmail.apach.dima.batch_demo.manager.application.job.model.BatchStatus;
import com.gmail.apach.dima.batch_demo.manager.application.receiver.RequestParametersReceiver;
import com.gmail.apach.dima.batch_demo.manager.infrastructure.output.rest.config.worker.BatchWorkerClientUriConfig;
import com.gmail.apach.dima.batch_demo.manager.infrastructure.output.rest.mapper.BatchWorkerRestMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExecuteJobRestServiceTest {

    private static final String URI = "execute-job-uri";

    @InjectMocks
    private ExecuteJobRestService executeJobRestService;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private RestClient restClient;
    @Mock
    private BatchWorkerRestMapper batchWorkerRestMapper;
    @Mock
    private BatchWorkerClientUriConfig clientUriConfig;

    @Test
    void execute_success() {
        final var jobExecutionRequest = mock(WorkerJobExecutionRequest.class);
        final var requestParameters = RequestParametersReceiver.parameters();
        final var jobName = requestParameters.get(RequestParameter.JOB_NAME);
        final var marker = requestParameters.get(RequestParameter.JOB_EXECUTION_MARKER);
        final var response = JobExecutionResult.builder()
            .info(JobExecutionInfo.builder()
                .jobName(jobName)
                .jobExecutionMarker(marker)
                .batchStatus(BatchStatus.COMPLETED.getStatus())
                .build())
            .build();

        when(batchWorkerRestMapper.toJobExecutionRequest(requestParameters))
            .thenReturn(jobExecutionRequest);
        when(clientUriConfig.executeJobUri())
            .thenReturn(URI);
        when(restClient.post()
            .uri(URI)
            .contentType(MediaType.APPLICATION_JSON)
            .body(jobExecutionRequest)
            .exchange(any()))
            .thenReturn(response);

        final var actual = executeJobRestService.execute(requestParameters);

        assertNotNull(actual);
        assertNull(actual.errors());
        assertNotNull(actual.info());
        assertEquals(jobName, actual.info().jobName());
        assertEquals(marker, actual.info().jobExecutionMarker());
    }

    @Test
    void execute_fail() {
        final var jobExecutionRequest = mock(WorkerJobExecutionRequest.class);
        final var errorMessage = "errors-message";
        final var internalServerError = HttpStatus.INTERNAL_SERVER_ERROR;
        final var response = JobExecutionResult.builder()
            .errors(RestClientErrors.builder()
                .status(internalServerError)
                .message(errorMessage)
                .build())
            .build();
        final var requestParameters = RequestParametersReceiver.parameters();

        when(batchWorkerRestMapper.toJobExecutionRequest(requestParameters))
            .thenReturn(jobExecutionRequest);
        when(clientUriConfig.executeJobUri())
            .thenReturn(URI);
        when(restClient.post()
            .uri(URI)
            .contentType(MediaType.APPLICATION_JSON)
            .body(jobExecutionRequest)
            .exchange(any()))
            .thenReturn(response);

        final var actual = executeJobRestService.execute(requestParameters);

        assertNotNull(actual);
        assertNull(actual.info());
        assertNotNull(actual.errors());
        assertEquals(internalServerError, actual.errors().status());
        assertEquals(errorMessage, actual.errors().message());
    }
}