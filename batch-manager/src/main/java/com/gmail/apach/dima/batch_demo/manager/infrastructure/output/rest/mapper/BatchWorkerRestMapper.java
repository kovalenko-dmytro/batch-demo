package com.gmail.apach.dima.batch_demo.manager.infrastructure.output.rest.mapper;

import com.gmail.apach.dima.batch_demo.common.dto.BatchWorkerJobExecutionRequest;
import com.gmail.apach.dima.batch_demo.common.model.RequestParameter;
import com.gmail.apach.dima.batch_demo.common.model.RequestParameters;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class BatchWorkerRestMapper {

    public BatchWorkerJobExecutionRequest toJobExecutionRequest(@NonNull RequestParameters parameters) {
        return BatchWorkerJobExecutionRequest.builder()
            .jobName(parameters.get(RequestParameter.JOB_NAME))
            .jobExecutionMarker(parameters.get(RequestParameter.JOB_EXECUTION_MARKER))
            .fileStorageResource(parameters.get(RequestParameter.FILE_STORAGE_RESOURCE))
            .build();
    }
}
