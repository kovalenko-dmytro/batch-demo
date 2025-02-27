package com.gmail.apach.dima.batch_demo.infrastructure.adapter.input.shell.common.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ShellArgument {

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class JobName {
        public static final String NAME = "job-name";
        public static final String HELP = "Provide job name, should be defined and not blank";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class FileStorageResource {
        public static final String NAME = "file-storage-resource";
        public static final String HELP = "Provide file storage resource key, can be optional if not required";
    }
}
