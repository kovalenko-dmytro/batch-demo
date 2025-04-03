package com.gmail.apach.dima.batch_demo.manager.port.input.file;

import com.gmail.apach.dima.batch_demo.common.model.StoredResource;
import org.springframework.web.multipart.MultipartFile;

public interface UploadFileInputPort {

    StoredResource upload(MultipartFile file);
}
