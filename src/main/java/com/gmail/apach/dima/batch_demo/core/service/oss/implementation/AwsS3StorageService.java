package com.gmail.apach.dima.batch_demo.core.service.oss.implementation;

import com.gmail.apach.dima.batch_demo.core.config.message.constant.Error;
import com.gmail.apach.dima.batch_demo.core.config.oss.AwsS3Properties;
import com.gmail.apach.dima.batch_demo.core.exception.ObjectStorageException;
import com.gmail.apach.dima.batch_demo.core.model.oss.StoredResource;
import com.gmail.apach.dima.batch_demo.core.service.message.MessageService;
import com.gmail.apach.dima.batch_demo.core.service.oss.ObjectStorageService;
import com.gmail.apach.dima.batch_demo.core.service.oss.mapper.AwsS3Mapper;
import io.awspring.cloud.s3.S3Resource;
import io.awspring.cloud.s3.S3Template;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class AwsS3StorageService implements ObjectStorageService {

    private final S3Template s3Template;
    private final AwsS3Properties properties;
    private final AwsS3Mapper awsS3Mapper;
    private final MessageService messageService;

    @Override
    public StoredResource save(@NotNull MultipartFile file) {
        final var objectKey = UUID.randomUUID().toString();
        try {
            final var resource = s3Template.upload(properties.getBucket(), objectKey, file.getInputStream());
            return awsS3Mapper.toStoredResource(resource);
        } catch (IOException e) {
            log.error(messageService.message(Error.FILE_UNABLE_UPLOAD, file.getOriginalFilename(), e.getMessage()));
            return new StoredResource();
        }
    }

    @Override
    public StoredResource get(@NotNull String objectKey) throws ObjectStorageException {
        S3Resource resource;
        try {
            resource = s3Template.download(properties.getBucket(), objectKey);
        } catch (RuntimeException e) {
            throw new ObjectStorageException(e.getMessage());
        }
        return awsS3Mapper.toStoredResource(resource);
    }

    @Override
    public void delete(@NotNull String objectKey) {
        s3Template.deleteObject(properties.getBucket(), objectKey);
    }
}
