package com.gmail.apach.dima.batch_demo.example.mapper;

import com.gmail.apach.dima.batch_demo.domain.entity.ExampleEntity;
import com.gmail.apach.dima.batch_demo.example.model.ExampleLine;
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
