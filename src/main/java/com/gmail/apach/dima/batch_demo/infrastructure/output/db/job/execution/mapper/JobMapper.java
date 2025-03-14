package com.gmail.apach.dima.batch_demo.infrastructure.output.db.job.execution.mapper;

import com.gmail.apach.dima.batch_demo.application.core.job.model.BatchStatus;
import com.gmail.apach.dima.batch_demo.application.core.job.model.ExecutedJob;
import com.gmail.apach.dima.batch_demo.application.core.job.model.ExitCode;
import com.gmail.apach.dima.batch_demo.infrastructure.output.db.job.execution.view.JobView;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;


@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL,
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
@SuppressWarnings("unused")
public interface JobMapper {

    ExecutedJob toExecutedJob(JobView view);

    default BatchStatus toBatchStatus(String status) {
        return BatchStatus.from(status);
    }

    default ExitCode toExitCode(String exitCode) {
        return ExitCode.from(exitCode);
    }
}
