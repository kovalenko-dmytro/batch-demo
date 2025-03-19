package com.gmail.apach.dima.batch_demo.infrastructure.input.web.common.mapper;

import com.gmail.apach.dima.batch_demo.application.core.job.model.*;
import com.gmail.apach.dima.batch_demo.infrastructure.input.web.controller.batch.dto.ExecuteJobRequest;
import com.gmail.apach.dima.batch_demo.infrastructure.input.web.controller.batch.dto.GetExecutedJobResponse;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.springframework.lang.NonNull;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL,
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
@SuppressWarnings("unused")
public interface JobRestMapper {
    GetExecutedJobResponse toGetExecutedJobResponse(ExecutedJob executedJob);

    default String toBatchStatus(BatchStatus status) {
        return Optional.ofNullable(status)
            .map(BatchStatus::name)
            .orElse(BatchStatus.UNKNOWN.name());
    }

    default String toExitCode(ExitCode exitCode) {
        return Optional.ofNullable(exitCode)
            .map(ExitCode::name)
            .orElse(ExitCode.UNKNOWN.name());
    }

    default List<String> toRegisteredJobNames(@NonNull List<RegisteredJob> registeredJobs) {
        return registeredJobs.stream().map(RegisteredJob::getName).toList();
    }

    default RequestParameters toRequestParameters(ExecuteJobRequest request) {
        final var result = new HashMap<RequestParameter, String>();
        result.put(RequestParameter.JOB_NAME, request.jobName());
        if (Objects.nonNull(request.fileStorageResource())) {
            result.put(RequestParameter.FILE_STORAGE_RESOURCE, request.fileStorageResource());
        }
        return new RequestParameters(result);
    }
}
