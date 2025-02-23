package com.gmail.apach.dima.batch_demo.application.input.file;

import com.gmail.apach.dima.batch_demo.core.base.file.model.StoredResource;

public interface DownloadFileInputPort {
    StoredResource download(String fileStorageKey);
}
