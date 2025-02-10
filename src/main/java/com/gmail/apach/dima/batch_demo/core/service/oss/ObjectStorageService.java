package com.gmail.apach.dima.batch_demo.core.service.oss;

import com.gmail.apach.dima.batch_demo.core.exception.ObjectStorageException;
import com.gmail.apach.dima.batch_demo.core.model.oss.StoredResource;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public interface ObjectStorageService {

    StoredResource save(@NotNull MultipartFile file);

    StoredResource get(@NotNull String objectKey) throws ObjectStorageException;

    void delete(@NotNull String objectKey);
}
