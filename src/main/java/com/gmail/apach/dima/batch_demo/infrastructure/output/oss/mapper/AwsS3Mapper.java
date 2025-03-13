package com.gmail.apach.dima.batch_demo.infrastructure.output.oss.mapper;

import com.gmail.apach.dima.batch_demo.application.core.file.model.StoredResource;
import com.gmail.apach.dima.batch_demo.common.exception.ObjectStorageException;
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
    default byte[] getPayload(S3Resource resource) throws ObjectStorageException {
        try {
            return resource.getInputStream().readAllBytes();
        } catch (IOException e) {
            throw new ObjectStorageException(e.getMessage());
        }
    }
}
