package com.gmail.apach.dima.batch_demo.infrastructure.adapter.input.shell.common.mapper;

import com.gmail.apach.dima.batch_demo.application.core.job.model.RequestParameter;
import com.gmail.apach.dima.batch_demo.application.core.job.model.RequestParameters;
import com.gmail.apach.dima.batch_demo.infrastructure.adapter.input.shell.common.dto.ExecuteJobShellArgsWrapper;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

@Component
public class ShellMapper {

    public RequestParameters toParameters(@NonNull ExecuteJobShellArgsWrapper wrapper) {
        final var result = new HashMap<RequestParameter, String>();
        result.put(RequestParameter.JOB_NAME, wrapper.jobName());
        if (Objects.nonNull(wrapper.fileStorageResource())) {
            result.put(RequestParameter.FILE_STORAGE_RESOURCE, wrapper.fileStorageResource());
        }
        result.put(RequestParameter.JOB_EXEC_MARK, UUID.randomUUID().toString());
        return new RequestParameters(result);
    }
}
