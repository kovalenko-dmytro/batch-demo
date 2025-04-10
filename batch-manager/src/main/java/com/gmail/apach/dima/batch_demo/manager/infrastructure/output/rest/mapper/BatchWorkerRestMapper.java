package com.gmail.apach.dima.batch_demo.manager.infrastructure.output.rest.mapper;

import com.gmail.apach.dima.batch_demo.common.dto.RestApiErrorResponse;
import com.gmail.apach.dima.batch_demo.common.dto.WorkerJobExecutionRequest;
import com.gmail.apach.dima.batch_demo.common.dto.WorkerJobExecutionResponse;
import com.gmail.apach.dima.batch_demo.common.model.*;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class BatchWorkerRestMapper {

    public WorkerJobExecutionRequest toJobExecutionRequest(@NonNull RequestParameters parameters) {
        return WorkerJobExecutionRequest.builder()
            .jobName(parameters.get(RequestParameter.JOB_NAME))
            .jobExecutionMarker(parameters.get(RequestParameter.JOB_EXECUTION_MARKER))
            .fileStorageResource(parameters.get(RequestParameter.FILE_STORAGE_RESOURCE))
            .build();
    }

    public JobExecutionResult toErrorExecutionResult(@NonNull RestApiErrorResponse response) {
        return JobExecutionResult.builder()
            .error(JobExecutionError.builder()
                .status(response.status())
                .message(response.message())
                .errors(response.errors())
                .timestamp(response.timestamp())
                .build())
            .build();
    }

    public JobExecutionResult toSuccessExecutionResult(@NonNull WorkerJobExecutionResponse response) {
        return JobExecutionResult.builder()
            .info(JobExecutionInfo.builder()
                .jobName(response.jobName())
                .jobExecutionMarker(response.jobExecutionMarker())
                .batchStatus(response.batchStatus())
                .build())
            .build();
    }
}
