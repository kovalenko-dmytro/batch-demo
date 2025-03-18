package com.gmail.apach.dima.batch_demo.infrastructure.output.db.job.registry.mapper;

import com.gmail.apach.dima.batch_demo.application.core.job.model.RegisteredJob;
import org.mapstruct.*;


@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL,
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
@SuppressWarnings("unused")
public interface RegisteredJobMapper {

    @Mapping(target = "name", source = "jobName")
    RegisteredJob toRegisteredJob(String jobName);
}
