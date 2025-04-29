package com.gmail.apach.dima.batch_demo.manager.infrastructure.input.common.mapper;

import com.gmail.apach.dima.batch_demo.common.model.JobExecutionResult;
import com.gmail.apach.dima.batch_demo.common.model.RequestParameter;
import com.gmail.apach.dima.batch_demo.common.model.RequestParameters;
import com.gmail.apach.dima.batch_demo.manager.application.job.model.BatchStatus;
import com.gmail.apach.dima.batch_demo.manager.application.job.model.ExecutedJob;
import com.gmail.apach.dima.batch_demo.manager.application.job.model.ExitCode;
import com.gmail.apach.dima.batch_demo.manager.application.job.model.RegisteredJob;
import com.gmail.apach.dima.batch_demo.manager.infrastructure.input.controller.batch.dto.ExecuteJobRequest;
import com.gmail.apach.dima.batch_demo.manager.infrastructure.input.controller.batch.dto.ExecuteJobResponse;
import com.gmail.apach.dima.batch_demo.manager.infrastructure.input.controller.batch.dto.GetExecutedJobResponse;
import org.mapstruct.*;
import org.springframework.lang.NonNull;

import java.util.HashMap;
import java.util.List;
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
        result.put(RequestParameter.JOB_EXECUTION_MARKER, request.defineJobExecutionMarker());

        Optional.ofNullable(request.fileStorageResource())
            .ifPresent(resource -> result.put(RequestParameter.FILE_STORAGE_RESOURCE, resource.trim()));

        return new RequestParameters(result);
    }

    @Mapping(target = "jobName", source = "info.jobName")
    @Mapping(target = "jobExecutionMarker", source = "info.jobExecutionMarker")
    @Mapping(target = "batchStatus", source = "info.batchStatus")
    @Mapping(target = "failures", source = "info.failures")
    @Mapping(target = "restClientErrors.status", source = "errors.status")
    @Mapping(target = "restClientErrors.message", source = "errors.message")
    @Mapping(target = "restClientErrors.errors", source = "errors.errors")
    @Mapping(target = "restClientErrors.timestamp", source = "errors.timestamp")
    ExecuteJobResponse toExecuteJobResponse(JobExecutionResult jobExecutionResult);
}
