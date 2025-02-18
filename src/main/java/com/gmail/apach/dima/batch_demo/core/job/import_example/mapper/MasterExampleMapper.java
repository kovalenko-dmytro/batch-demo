package com.gmail.apach.dima.batch_demo.core.job.import_example.mapper;

import com.gmail.apach.dima.batch_demo.infrastructure.adapter.output.db.example.entity.MasterExampleEntity;
import com.gmail.apach.dima.batch_demo.infrastructure.adapter.output.db.example.entity.WorkExampleEntity;
import org.mapstruct.*;
import org.springframework.validation.annotation.Validated;

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL,
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Validated
public interface MasterExampleMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "column1", source = "fieldParam1")
    @Mapping(target = "column2", source = "fieldParam2")
    @Mapping(target = "column3", source = "fieldParam3")
    @Mapping(target = "column4", source = "fieldParam4")
    MasterExampleEntity toExampleEntity(WorkExampleEntity work);
}
