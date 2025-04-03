package com.gmail.apach.dima.batch_demo.manager.port.output.oss;

import com.gmail.apach.dima.batch_demo.common.model.StoredResource;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public interface ObjectStorageServiceOutputPort {

    StoredResource save(@NotNull MultipartFile file);

    StoredResource get(@NotNull String key);
}
