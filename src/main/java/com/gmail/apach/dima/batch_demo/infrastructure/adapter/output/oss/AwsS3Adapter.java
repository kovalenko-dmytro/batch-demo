package com.gmail.apach.dima.batch_demo.infrastructure.adapter.output.oss;

import com.gmail.apach.dima.batch_demo.application.output.oss.AwsS3OutputPort;
import com.gmail.apach.dima.batch_demo.core.base.model.oss.StoredResource;
import com.gmail.apach.dima.batch_demo.infrastructure.adapter.output.oss.config.AwsS3Properties;
import com.gmail.apach.dima.batch_demo.infrastructure.adapter.output.oss.exception.ObjectStorageException;
import com.gmail.apach.dima.batch_demo.infrastructure.adapter.output.oss.mapper.AwsS3Mapper;
import com.gmail.apach.dima.batch_demo.infrastructure.common.message.MessageUtil;
import com.gmail.apach.dima.batch_demo.infrastructure.common.message.code.Error;
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
public class AwsS3Adapter implements AwsS3OutputPort {

    private final S3Template s3Template;
    private final AwsS3Properties properties;
    private final AwsS3Mapper awsS3Mapper;
    private final MessageUtil messageUtil;

    @Override
    public StoredResource save(@NotNull MultipartFile file) {
        final var objectKey = UUID.randomUUID().toString();
        try {
            final var resource = s3Template.upload(properties.getS3().getBucket(), objectKey, file.getInputStream());
            return awsS3Mapper.toStoredResource(resource);
        } catch (IOException e) {
            log.error(messageUtil.getMessage(Error.FILE_UNABLE_UPLOAD, file.getOriginalFilename(), e.getMessage()));
            return new StoredResource();
        }
    }

    @Override
    public StoredResource get(@NotNull String key) throws ObjectStorageException {
        final var resource = s3Template.download(properties.getS3().getBucket(), key);
        return awsS3Mapper.toStoredResource(resource);
    }

    @Override
    public void delete(@NotNull String key) {
        s3Template.deleteObject(properties.getS3().getBucket(), key);
    }
}
