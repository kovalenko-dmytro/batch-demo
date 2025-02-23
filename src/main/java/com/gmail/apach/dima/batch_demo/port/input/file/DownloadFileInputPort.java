package com.gmail.apach.dima.batch_demo.port.input.file;

import com.gmail.apach.dima.batch_demo.application.core.file.model.StoredResource;

public interface DownloadFileInputPort {
    StoredResource download(String fileStorageKey);
}
