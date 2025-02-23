package com.gmail.apach.dima.batch_demo.application.batch.import_csv_to_db.mapper;

import com.gmail.apach.dima.batch_demo.infrastructure.adapter.output.db.master.entity.MasterTableEntity;
import com.gmail.apach.dima.batch_demo.infrastructure.adapter.output.db.work.entity.WorkTableEntity;
import org.mapstruct.*;
import org.springframework.validation.annotation.Validated;

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL,
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Validated
public interface MasterMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "column1", source = "fieldParam1")
    @Mapping(target = "column2", source = "fieldParam2")
    @Mapping(target = "column3", source = "fieldParam3")
    @Mapping(target = "column4", source = "fieldParam4")
    MasterTableEntity toMasterTableEntity(WorkTableEntity work);
}
