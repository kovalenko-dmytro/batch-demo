package com.gmail.apach.dima.batch_demo.core.base.file.service;

import com.gmail.apach.dima.batch_demo.application.input.file.DownloadFileInputPort;
import com.gmail.apach.dima.batch_demo.application.output.oss.AwsS3OutputPort;
import com.gmail.apach.dima.batch_demo.core.base.file.model.StoredResource;
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
