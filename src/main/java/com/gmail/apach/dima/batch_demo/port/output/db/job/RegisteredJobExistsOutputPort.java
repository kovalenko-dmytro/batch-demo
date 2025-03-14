package com.gmail.apach.dima.batch_demo.port.output.db.job;

public interface RegisteredJobExistsOutputPort {
    boolean exist(String jobName);
}
