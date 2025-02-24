package com.gmail.apach.dima.batch_demo.port.output.oss;

import com.gmail.apach.dima.batch_demo.application.core.file.model.StoredResource;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface AwsS3OutputPort extends BaseOssOutputPort<MultipartFile, String> {
    @Override
    StoredResource save(MultipartFile file);

    StoredResource save(@NotNull File file);

    @Override
    StoredResource get(String key);

    @Override
    void delete(String key);
}
