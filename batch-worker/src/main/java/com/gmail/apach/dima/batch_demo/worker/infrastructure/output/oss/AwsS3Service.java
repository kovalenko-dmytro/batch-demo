package com.gmail.apach.dima.batch_demo.worker.infrastructure.output.oss;

import com.gmail.apach.dima.batch_demo.common.constant.Error;
import com.gmail.apach.dima.batch_demo.common.exception.ObjectStorageException;
import com.gmail.apach.dima.batch_demo.common.model.StoredResource;
import com.gmail.apach.dima.batch_demo.common.util.MessageUtil;
import com.gmail.apach.dima.batch_demo.worker.infrastructure.output.oss.config.AwsS3Properties;
import com.gmail.apach.dima.batch_demo.worker.infrastructure.output.oss.mapper.AwsS3Mapper;
import com.gmail.apach.dima.batch_demo.worker.port.output.oss.ObjectStorageServiceOutputPort;
import io.awspring.cloud.s3.S3Template;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class AwsS3Service implements ObjectStorageServiceOutputPort {

    private final S3Template s3Template;
    private final AwsS3Properties properties;
    private final AwsS3Mapper awsS3Mapper;
    private final MessageUtil messageUtil;

    @Override
    public StoredResource save(@NotNull File file) {
        final var objectKey = file.getName();
        try (final var is = new FileInputStream(file)) {
            final var resource = s3Template.upload(properties.getS3().getBucket(), objectKey, is);
            return awsS3Mapper.toStoredResource(resource);
        } catch (IOException e) {
            log.error(messageUtil.getMessage(Error.FILE_UNABLE_UPLOAD, file.getName(), e.getMessage()));
            return new StoredResource();
        }
    }

    @Override
    public StoredResource get(@NotNull String key) throws ObjectStorageException {
        final var resource = s3Template.download(properties.getS3().getBucket(), key);
        return awsS3Mapper.toStoredResource(resource);
    }
}
