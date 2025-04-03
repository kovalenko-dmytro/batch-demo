package com.gmail.apach.dima.batch_demo.worker.port.output.oss;

import com.gmail.apach.dima.batch_demo.common.model.StoredResource;
import jakarta.validation.constraints.NotNull;

import java.io.File;

public interface ObjectStorageServiceOutputPort {

    StoredResource save(@NotNull File file);

    StoredResource get(@NotNull String key);
}
