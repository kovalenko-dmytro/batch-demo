package com.gmail.apach.dima.batch_demo.infrastructure.input.web.common.mapper;

import com.gmail.apach.dima.batch_demo.application.core.job.model.RequestParameter;
import com.gmail.apach.dima.batch_demo.application.core.job.model.RequestParameters;
import com.gmail.apach.dima.batch_demo.infrastructure.input.web.controller.batch.dto.ExecuteJobRequest;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Objects;

@Component
public class ExecuteJobRestMapper {

    public RequestParameters toRequestParameters(ExecuteJobRequest request) {
        final var result = new HashMap<RequestParameter, String>();
        result.put(RequestParameter.JOB_NAME, request.jobName());
        if (Objects.nonNull(request.fileStorageResource())) {
            result.put(RequestParameter.FILE_STORAGE_RESOURCE, request.fileStorageResource());
        }
        return new RequestParameters(result);
    }
}
