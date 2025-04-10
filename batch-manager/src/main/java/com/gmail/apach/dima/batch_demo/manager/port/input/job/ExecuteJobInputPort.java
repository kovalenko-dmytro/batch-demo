package com.gmail.apach.dima.batch_demo.manager.port.input.job;

import com.gmail.apach.dima.batch_demo.common.model.JobExecutionResult;
import com.gmail.apach.dima.batch_demo.common.model.RequestParameters;

public interface ExecuteJobInputPort {

    JobExecutionResult execute(RequestParameters parameters);
}
