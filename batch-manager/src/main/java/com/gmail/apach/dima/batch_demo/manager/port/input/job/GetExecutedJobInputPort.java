package com.gmail.apach.dima.batch_demo.manager.port.input.job;

import com.gmail.apach.dima.batch_demo.manager.application.job.model.ExecutedJob;

public interface GetExecutedJobInputPort {

    ExecutedJob get(String jobExecutionMarker);
}
