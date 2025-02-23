package com.gmail.apach.dima.batch_demo.infrastructure.adapter.input.web.common.mapper;

import com.gmail.apach.dima.batch_demo.core.base.file.model.StoredResource;
import com.gmail.apach.dima.batch_demo.infrastructure.adapter.input.web.file.dto.FileResponse;
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
