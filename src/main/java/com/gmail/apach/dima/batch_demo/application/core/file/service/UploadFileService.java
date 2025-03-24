package com.gmail.apach.dima.batch_demo.application.core.file.service;

import com.gmail.apach.dima.batch_demo.application.core.file.model.StoredResource;
import com.gmail.apach.dima.batch_demo.port.input.file.UploadFileInputPort;
import com.gmail.apach.dima.batch_demo.port.output.oss.OssOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
public class UploadFileService implements UploadFileInputPort {

    private final OssOutputPort ossOutputPort;

    @Override
    public StoredResource upload(MultipartFile file) {
        return ossOutputPort.save(file);
    }
}
