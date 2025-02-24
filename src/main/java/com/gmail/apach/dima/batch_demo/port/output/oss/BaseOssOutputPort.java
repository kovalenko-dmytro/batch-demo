package com.gmail.apach.dima.batch_demo.port.output.oss;

import com.gmail.apach.dima.batch_demo.application.core.file.model.StoredResource;
import jakarta.validation.constraints.NotNull;

@SuppressWarnings("unused")
public interface BaseOssOutputPort<F, K> {
    StoredResource save(@NotNull F file);

    StoredResource get(@NotNull K key);

    void delete(@NotNull K key);
}
