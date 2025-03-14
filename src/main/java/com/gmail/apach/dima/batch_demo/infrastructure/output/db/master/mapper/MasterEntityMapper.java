package com.gmail.apach.dima.batch_demo.infrastructure.output.db.master.mapper;

import com.gmail.apach.dima.batch_demo.application.batch.import_csv_to_db.model.MasterModel;
import com.gmail.apach.dima.batch_demo.infrastructure.output.db.master.entity.MasterTableEntity;
import org.mapstruct.*;

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL,
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MasterEntityMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "column1", source = "column1")
    @Mapping(target = "column2", source = "column2")
    @Mapping(target = "column3", source = "column3")
    @Mapping(target = "column4", source = "column4")
    MasterModel toMasterModel(MasterTableEntity masterTableEntity);

    @Mapping(target = "column1", source = "column1")
    @Mapping(target = "column2", source = "column2")
    @Mapping(target = "column3", source = "column3")
    @Mapping(target = "column4", source = "column4")
    MasterTableEntity toMasterTableEntity(MasterModel masterModel);
}
