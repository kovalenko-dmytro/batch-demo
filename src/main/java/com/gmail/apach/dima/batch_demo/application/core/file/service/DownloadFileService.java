package com.gmail.apach.dima.batch_demo.application.core.file.service;

import com.gmail.apach.dima.batch_demo.application.core.file.model.StoredResource;
import com.gmail.apach.dima.batch_demo.port.input.file.DownloadFileInputPort;
import com.gmail.apach.dima.batch_demo.port.output.oss.OssOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DownloadFileService implements DownloadFileInputPort {

    private final OssOutputPort ossOutputPort;

    @Override
    public StoredResource download(String fileStorageKey) {
        return ossOutputPort.get(fileStorageKey);
    }
}
