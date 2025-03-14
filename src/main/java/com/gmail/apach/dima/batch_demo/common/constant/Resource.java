package com.gmail.apach.dima.batch_demo.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Resource {

    JOB("Job");

    private final String name;

    @AllArgsConstructor
    @Getter
    public enum Attribute {

        JOB_EXEC_MARK("job-exec-mark");

        private final String name;
    }
}
