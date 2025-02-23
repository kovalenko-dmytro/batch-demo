package com.gmail.apach.dima.batch_demo.infrastructure.adapter.output.oss.mapper;

import com.gmail.apach.dima.batch_demo.core.base.file.model.StoredResource;
import io.awspring.cloud.s3.S3Resource;
import org.mapstruct.*;

import java.io.IOException;

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL,
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
@SuppressWarnings("unused")
public interface AwsS3Mapper {

    @Mapping(target = "storageKey", source = "resource.location.object")
    @Mapping(target = "fileName", source = "resource", qualifiedByName = "getFileName")
    @Mapping(target = "payload", source = "resource", qualifiedByName = "getPayload")
    StoredResource toStoredResource(S3Resource resource);

    @Named("getFileName")
    default String getFileName(S3Resource resource) {
        return resource.getFilename();
    }

    @Named("getPayload")
    default byte[] getPayload(S3Resource resource) throws IOException {
        return resource.getInputStream().readAllBytes();
    }
}
