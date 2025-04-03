package com.gmail.apach.dima.batch_demo.manager.infrastructure.output.rest;

import com.gmail.apach.dima.batch_demo.common.dto.BatchWorkerJobExecutionRequest;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
        final var jobExecutionRequest = mock(BatchWorkerJobExecutionRequest.class);
        final var response = new ResponseEntity<Void>(HttpStatus.CREATED);
        final var requestParameters = RequestParametersReceiver.parameters();

        when(batchWorkerRestMapper.toJobExecutionRequest(requestParameters))
            .thenReturn(jobExecutionRequest);
        when(clientUriConfig.executeJobUri())
            .thenReturn(URI);
        when(restClient.post()
            .uri(URI)
            .contentType(MediaType.APPLICATION_JSON)
            .body(jobExecutionRequest)
            .retrieve()
            .toBodilessEntity())
            .thenReturn(response);

        final var actual = executeJobRestService.execute(requestParameters);

        assertNotNull(actual);
        assertEquals(HttpStatus.CREATED, actual);
    }

    @Test
    void execute_fail() {
        final var jobExecutionRequest = mock(BatchWorkerJobExecutionRequest.class);
        final var response = new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        final var requestParameters = RequestParametersReceiver.parameters();

        when(batchWorkerRestMapper.toJobExecutionRequest(requestParameters))
            .thenReturn(jobExecutionRequest);
        when(clientUriConfig.executeJobUri())
            .thenReturn(URI);
        when(restClient.post()
            .uri(URI)
            .contentType(MediaType.APPLICATION_JSON)
            .body(jobExecutionRequest)
            .retrieve()
            .toBodilessEntity())
            .thenReturn(response);

        final var actual = executeJobRestService.execute(requestParameters);

        assertNotNull(actual);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, actual);
    }
}