package com.gmail.apach.dima.batch_demo.application.batch.import_csv_to_db.mapper;

import com.gmail.apach.dima.batch_demo.application.batch.import_csv_to_db.model.MasterModel;
import com.gmail.apach.dima.batch_demo.infrastructure.adapter.output.db.master.entity.MasterTableEntity;
import com.gmail.apach.dima.batch_demo.infrastructure.adapter.output.db.work.entity.WorkTableEntity;
import jakarta.validation.Valid;
import org.mapstruct.*;
import org.springframework.validation.annotation.Validated;

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL,
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Validated
public interface MasterMapper {

    @Mapping(target = "column1", source = "fieldParam1")
    @Mapping(target = "column2", source = "fieldParam2")
    @Mapping(target = "column3", source = "fieldParam3")
    @Mapping(target = "column4", source = "fieldParam4")
    MasterModel toMasterModel(WorkTableEntity work);

    @Mapping(target = "column1", source = "column1")
    @Mapping(target = "column2", source = "column2")
    @Mapping(target = "column3", source = "column3")
    @Mapping(target = "column4", source = "column4")
    MasterTableEntity toMasterTableEntity(@Valid MasterModel masterModel);
}
