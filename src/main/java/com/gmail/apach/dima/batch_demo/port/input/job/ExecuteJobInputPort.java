package com.gmail.apach.dima.batch_demo.port.input.job;

import com.gmail.apach.dima.batch_demo.application.core.job.model.RequestParameters;

public interface ExecuteJobInputPort {
    void execute(RequestParameters parameters);
}
