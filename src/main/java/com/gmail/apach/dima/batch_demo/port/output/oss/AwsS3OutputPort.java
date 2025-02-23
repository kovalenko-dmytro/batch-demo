package com.gmail.apach.dima.batch_demo.port.output.oss;

import com.gmail.apach.dima.batch_demo.application.core.file.model.StoredResource;
import org.springframework.web.multipart.MultipartFile;

public interface AwsS3OutputPort extends BaseOssOutputPort<MultipartFile, String> {
    @Override
    StoredResource save(MultipartFile file);

    @Override
    StoredResource get(String key);

    @Override
    void delete(String key);
}
