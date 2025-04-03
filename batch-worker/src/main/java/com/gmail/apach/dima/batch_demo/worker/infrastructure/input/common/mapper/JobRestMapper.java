package com.gmail.apach.dima.batch_demo.worker.infrastructure.input.common.mapper;

import com.gmail.apach.dima.batch_demo.common.dto.BatchWorkerJobExecutionRequest;
import com.gmail.apach.dima.batch_demo.common.model.RequestParameter;
import com.gmail.apach.dima.batch_demo.common.model.RequestParameters;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.HashMap;
import java.util.Optional;

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL,
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
@SuppressWarnings("unused")
public interface JobRestMapper {

    default RequestParameters toRequestParameters(BatchWorkerJobExecutionRequest request) {
        final var result = new HashMap<RequestParameter, String>();

        result.put(RequestParameter.JOB_NAME, request.jobName());
        result.put(RequestParameter.JOB_EXECUTION_MARKER, request.jobExecutionMarker());

        Optional.ofNullable(request.fileStorageResource())
            .ifPresent(resource -> result.put(RequestParameter.FILE_STORAGE_RESOURCE, resource.trim()));

        return new RequestParameters(result);
    }
}
