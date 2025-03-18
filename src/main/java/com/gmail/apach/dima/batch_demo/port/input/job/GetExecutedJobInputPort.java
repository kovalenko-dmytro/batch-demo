package com.gmail.apach.dima.batch_demo.port.input.job;

import com.gmail.apach.dima.batch_demo.application.core.job.model.ExecutedJob;

public interface GetExecutedJobInputPort {
    ExecutedJob get(String jobExecutionMarker);
}
