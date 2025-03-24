package com.gmail.apach.dima.batch_demo.port.output.oss;

import com.gmail.apach.dima.batch_demo.application.core.file.model.StoredResource;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@SuppressWarnings("unused")
public interface OssOutputPort {
    StoredResource save(MultipartFile file);

    StoredResource save(@NotNull File file);

    StoredResource get(@NotNull String key);

    void delete(@NotNull String key);
}
