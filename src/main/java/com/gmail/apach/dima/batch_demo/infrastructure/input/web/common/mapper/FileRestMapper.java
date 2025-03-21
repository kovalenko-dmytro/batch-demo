package com.gmail.apach.dima.batch_demo.infrastructure.input.web.common.mapper;

import com.gmail.apach.dima.batch_demo.application.core.file.model.StoredResource;
import com.gmail.apach.dima.batch_demo.infrastructure.input.web.controller.file.dto.FileResponse;
import org.mapstruct.*;

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL,
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FileRestMapper {
    @Mapping(target = "storageKey", source = "storageKey")
    @Mapping(target = "fileName", source = "fileName")
    FileResponse toFileResponse(StoredResource storedResource);
}
