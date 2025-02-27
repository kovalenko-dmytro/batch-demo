package com.gmail.apach.dima.batch_demo.application.core.job.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JobExecutionContextKey {

    public static final String STORED_RESOURCE = "stored-resource";
    public static final String INSERTED_IDS = "inserted-ids";
    public static final String EXPORT_FILE_TEMP_PATH = "export-file-temp-path";
    public static final String TEMP_DIR_PATH = "temp-dir-path";
}
