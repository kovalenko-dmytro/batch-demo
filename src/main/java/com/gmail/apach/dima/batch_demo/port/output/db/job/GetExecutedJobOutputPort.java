package com.gmail.apach.dima.batch_demo.port.output.db.job;

import com.gmail.apach.dima.batch_demo.application.core.job.model.ExecutedJob;

public interface GetExecutedJobOutputPort {
    ExecutedJob get(String batchId);
}
