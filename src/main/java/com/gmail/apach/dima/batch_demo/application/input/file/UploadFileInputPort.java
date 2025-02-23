package com.gmail.apach.dima.batch_demo.application.input.file;

import com.gmail.apach.dima.batch_demo.core.base.file.model.StoredResource;
import org.springframework.web.multipart.MultipartFile;

public interface UploadFileInputPort {
    StoredResource upload(MultipartFile file);
}
