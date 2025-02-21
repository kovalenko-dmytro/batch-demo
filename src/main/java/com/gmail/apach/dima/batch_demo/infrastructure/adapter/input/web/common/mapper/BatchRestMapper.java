package com.gmail.apach.dima.batch_demo.infrastructure.adapter.input.web.common.mapper;

import com.gmail.apach.dima.batch_demo.core.base.model.job.RequestParameter;
import com.gmail.apach.dima.batch_demo.core.base.model.job.RequestParameters;
import com.gmail.apach.dima.batch_demo.infrastructure.adapter.input.web.batch.dto.JobExecutionRequest;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

@Component
public class BatchRestMapper {

    public RequestParameters toRequestParameters(JobExecutionRequest request) {
        final var result = new HashMap<RequestParameter, String>();
        result.put(RequestParameter.JOB_NAME, request.jobName());
        if (Objects.nonNull(request.fileStorageResource())) {
            result.put(RequestParameter.FILE_STORAGE_RESOURCE, request.fileStorageResource());
        }
        result.put(RequestParameter.JOB_EXEC_MARK, UUID.randomUUID().toString());
        return new RequestParameters(result);
    }
}
