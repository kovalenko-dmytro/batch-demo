package com.gmail.apach.dima.batch_demo.core.service.oss.implementation;

import com.gmail.apach.dima.batch_demo.core.model.oss.StoredResource;
import com.gmail.apach.dima.batch_demo.core.service.oss.ObjectStorageService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Component
@RequiredArgsConstructor
public class GcsStorageService implements ObjectStorageService {

    @Override
    public StoredResource save(@NotNull MultipartFile file) {
        throw new UnsupportedOperationException();
    }

    @Override
    public StoredResource get(@NotNull String objectKey) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(@NotNull String objectKey) {
        throw new UnsupportedOperationException();
    }
}
