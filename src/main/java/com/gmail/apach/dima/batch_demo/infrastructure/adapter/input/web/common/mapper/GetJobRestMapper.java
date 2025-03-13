package com.gmail.apach.dima.batch_demo.infrastructure.adapter.input.web.common.mapper;

import com.gmail.apach.dima.batch_demo.application.core.job.model.BatchStatus;
import com.gmail.apach.dima.batch_demo.application.core.job.model.ExecutedJob;
import com.gmail.apach.dima.batch_demo.application.core.job.model.ExitCode;
import com.gmail.apach.dima.batch_demo.infrastructure.adapter.input.web.controller.batch.dto.GetJobResponse;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.Optional;

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL,
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
@SuppressWarnings("unused")
public interface GetJobRestMapper {
    GetJobResponse toGetJobResponse(ExecutedJob executedJob);

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
}
