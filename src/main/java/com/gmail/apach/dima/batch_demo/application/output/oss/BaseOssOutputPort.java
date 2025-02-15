package com.gmail.apach.dima.batch_demo.application.output.oss;

import com.gmail.apach.dima.batch_demo.core.base.model.oss.StoredResource;
import jakarta.validation.constraints.NotNull;

@SuppressWarnings("unused")
public interface BaseOssOutputPort<F, K> {

    StoredResource save(@NotNull F file);

    StoredResource get(@NotNull K key);

    void delete(@NotNull K key);
}
