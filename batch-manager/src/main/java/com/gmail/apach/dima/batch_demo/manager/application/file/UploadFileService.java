package com.gmail.apach.dima.batch_demo.manager.application.file;

import com.gmail.apach.dima.batch_demo.common.model.StoredResource;
import com.gmail.apach.dima.batch_demo.manager.port.input.file.UploadFileInputPort;
import com.gmail.apach.dima.batch_demo.manager.port.output.oss.ObjectStorageServiceOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
public class UploadFileService implements UploadFileInputPort {

    private final ObjectStorageServiceOutputPort objectStorageServiceOutputPort;

    @Override
    public StoredResource upload(@NonNull MultipartFile file) {
        return objectStorageServiceOutputPort.save(file);
    }
}
