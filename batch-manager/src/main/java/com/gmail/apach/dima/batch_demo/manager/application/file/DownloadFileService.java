package com.gmail.apach.dima.batch_demo.manager.application.file;

import com.gmail.apach.dima.batch_demo.common.model.StoredResource;
import com.gmail.apach.dima.batch_demo.manager.port.input.file.DownloadFileInputPort;
import com.gmail.apach.dima.batch_demo.manager.port.output.oss.ObjectStorageServiceOutputPort;
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
