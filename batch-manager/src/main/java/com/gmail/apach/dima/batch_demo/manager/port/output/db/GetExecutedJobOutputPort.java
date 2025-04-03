package com.gmail.apach.dima.batch_demo.manager.port.output.db;

import com.gmail.apach.dima.batch_demo.manager.application.job.model.ExecutedJob;

public interface GetExecutedJobOutputPort {

    ExecutedJob get(String jobExecutionMarker);
}
