package com.gmail.apach.dima.batch_demo.manager.port.input.file;

import com.gmail.apach.dima.batch_demo.common.model.StoredResource;

public interface DownloadFileInputPort {

    StoredResource download(String fileStorageKey);
}
