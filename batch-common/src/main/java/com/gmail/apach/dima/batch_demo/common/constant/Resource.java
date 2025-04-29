package com.gmail.apach.dima.batch_demo.common.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Resource {

    JOB("Job");

    private final String name;

    @RequiredArgsConstructor
    @Getter
    public enum Attribute {

        JOB_EXEC_MARK("job-execution-marker"),
        NAME("name");

        private final String name;
    }
}
