package com.gmail.apach.dima.batch_demo.manager.infrastructure.output.rest;

import com.gmail.apach.dima.batch_demo.common.dto.RestApiErrorResponse;
import com.gmail.apach.dima.batch_demo.common.dto.WorkerJobExecutionResponse;
import com.gmail.apach.dima.batch_demo.common.model.JobExecutionResult;
import com.gmail.apach.dima.batch_demo.common.model.RequestParameters;
import com.gmail.apach.dima.batch_demo.manager.infrastructure.output.rest.config.worker.BatchWorkerClientUriConfig;
import com.gmail.apach.dima.batch_demo.manager.infrastructure.output.rest.mapper.BatchWorkerRestMapper;
import com.gmail.apach.dima.batch_demo.manager.port.output.rest.ExecuteJobOutputPort;
import lombok.RequiredArgsConstructor;
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
    public JobExecutionResult execute(@NonNull RequestParameters parameters) {
        final var request = batchWorkerRestMapper.toJobExecutionRequest(parameters);
        return restClient.post()
            .uri(clientUriConfig.executeJobUri())
            .contentType(MediaType.APPLICATION_JSON)
            .body(request)
            .exchange((req, resp) -> {
                if (resp.getStatusCode().isError()) {
                    final var errorResponse = resp.bodyTo(RestApiErrorResponse.class);
                    return batchWorkerRestMapper.toErrorExecutionResult(errorResponse);
                }
                final var successResponse = resp.bodyTo(WorkerJobExecutionResponse.class);
                return batchWorkerRestMapper.toSuccessExecutionResult(successResponse);
            });
    }
}
