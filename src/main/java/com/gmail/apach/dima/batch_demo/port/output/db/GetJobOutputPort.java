package com.gmail.apach.dima.batch_demo.port.output.db;

import com.gmail.apach.dima.batch_demo.application.core.job.model.ExecutedJob;

public interface GetJobOutputPort {
    ExecutedJob get(String batchId);
}
