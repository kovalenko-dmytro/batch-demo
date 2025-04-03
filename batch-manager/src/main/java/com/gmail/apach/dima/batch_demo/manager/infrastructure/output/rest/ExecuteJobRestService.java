package com.gmail.apach.dima.batch_demo.manager.infrastructure.output.rest;

import com.gmail.apach.dima.batch_demo.common.model.RequestParameters;
import com.gmail.apach.dima.batch_demo.manager.infrastructure.output.rest.config.worker.BatchWorkerClientUriConfig;
import com.gmail.apach.dima.batch_demo.manager.infrastructure.output.rest.mapper.BatchWorkerRestMapper;
import com.gmail.apach.dima.batch_demo.manager.port.output.rest.ExecuteJobOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
public class ExecuteJobRestService implements ExecuteJobOutputPort {

    private final RestClient restClient;
    private final BatchWorkerRestMapper batchWorkerRestMapper;
    private final BatchWorkerClientUriConfig clientUriConfig;

    @Override
    public HttpStatus execute(@NonNull RequestParameters parameters) {
        final var request = batchWorkerRestMapper.toJobExecutionRequest(parameters);
        final var response = restClient.post()
            .uri(clientUriConfig.executeJobUri())
            .contentType(MediaType.APPLICATION_JSON)
            .body(request)
            .retrieve()
            .toBodilessEntity();
        return response.getStatusCode().is2xxSuccessful()
            ? HttpStatus.CREATED
            : HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
