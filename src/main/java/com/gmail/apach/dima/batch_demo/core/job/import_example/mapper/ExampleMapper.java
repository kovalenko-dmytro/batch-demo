package com.gmail.apach.dima.batch_demo.core.job.import_example.mapper;

import com.gmail.apach.dima.batch_demo.core.job.import_example.model.ExampleLine;
import com.gmail.apach.dima.batch_demo.infrastructure.adapter.output.db.inventory.entity.ExampleEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL,
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ExampleMapper {

    ExampleEntity toExampleEntity(ExampleLine exampleLine);
}
