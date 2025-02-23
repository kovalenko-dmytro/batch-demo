package com.gmail.apach.dima.batch_demo.core.base.file.service;

import com.gmail.apach.dima.batch_demo.application.input.file.UploadFileInputPort;
import com.gmail.apach.dima.batch_demo.application.output.oss.AwsS3OutputPort;
import com.gmail.apach.dima.batch_demo.core.base.file.model.StoredResource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
public class UploadFileService implements UploadFileInputPort {

    private final AwsS3OutputPort awsS3OutputPort;

    @Override
    public StoredResource upload(MultipartFile file) {
        return awsS3OutputPort.save(file);
    }
}
