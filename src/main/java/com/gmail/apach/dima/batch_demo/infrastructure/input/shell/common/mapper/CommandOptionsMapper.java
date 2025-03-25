package com.gmail.apach.dima.batch_demo.infrastructure.input.shell.common.mapper;

import com.gmail.apach.dima.batch_demo.application.core.job.model.RequestParameter;
import com.gmail.apach.dima.batch_demo.application.core.job.model.RequestParameters;
import com.gmail.apach.dima.batch_demo.infrastructure.input.shell.common.dto.ExecuteJobOptionsWrapper;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Optional;

@Component
public class CommandOptionsMapper {

    public RequestParameters toRequestParameters(@NonNull ExecuteJobOptionsWrapper wrapper) {
        final var result = new HashMap<RequestParameter, String>();

        result.put(RequestParameter.JOB_NAME, wrapper.jobName());
        result.put(RequestParameter.JOB_EXECUTION_MARKER, wrapper.defineJobExecutionMarker());

        Optional.ofNullable(wrapper.fileStorageResource())
            .ifPresent(resource -> result.put(RequestParameter.FILE_STORAGE_RESOURCE, resource.trim()));

        return new RequestParameters(result);
    }
}
