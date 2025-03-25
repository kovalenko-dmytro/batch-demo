package com.gmail.apach.dima.batch_demo.application.core.file.service;

import com.gmail.apach.dima.batch_demo.application.core.file.model.StoredResource;
import com.gmail.apach.dima.batch_demo.port.input.file.DownloadFileInputPort;
import com.gmail.apach.dima.batch_demo.port.output.oss.ObjectStorageServiceOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DownloadFileService implements DownloadFileInputPort {

    private final ObjectStorageServiceOutputPort objectStorageServiceOutputPort;

    @Override
    public StoredResource download(@NonNull String fileStorageKey) {
        return objectStorageServiceOutputPort.get(fileStorageKey);
    }
}
