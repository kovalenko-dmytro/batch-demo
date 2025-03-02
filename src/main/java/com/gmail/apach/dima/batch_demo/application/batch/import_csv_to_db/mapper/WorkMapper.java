package com.gmail.apach.dima.batch_demo.application.batch.import_csv_to_db.mapper;

import com.gmail.apach.dima.batch_demo.application.batch.import_csv_to_db.model.WorkModel;
import com.gmail.apach.dima.batch_demo.infrastructure.adapter.output.db.work.entity.WorkTableEntity;
import jakarta.validation.Valid;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.springframework.validation.annotation.Validated;

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL,
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Validated
public interface WorkMapper {

    WorkTableEntity toWorkTableEntity(@Valid WorkModel workModel);
}
