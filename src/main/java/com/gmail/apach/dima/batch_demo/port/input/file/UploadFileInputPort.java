package com.gmail.apach.dima.batch_demo.port.input.file;

import com.gmail.apach.dima.batch_demo.application.core.file.model.StoredResource;
import org.springframework.web.multipart.MultipartFile;

public interface UploadFileInputPort {
    StoredResource upload(MultipartFile file);
}
