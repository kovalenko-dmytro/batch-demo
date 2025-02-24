package com.gmail.apach.dima.batch_demo.application.core.file.service;

import com.gmail.apach.dima.batch_demo.application.core.file.model.StoredResource;
import com.gmail.apach.dima.batch_demo.port.input.file.DownloadFileInputPort;
import com.gmail.apach.dima.batch_demo.port.output.oss.AwsS3OutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DownloadFileService implements DownloadFileInputPort {

    private final AwsS3OutputPort awsS3OutputPort;

    @Override
    public StoredResource download(String fileStorageKey) {
        return awsS3OutputPort.get(fileStorageKey);
    }
}
