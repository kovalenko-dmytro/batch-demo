package com.gmail.apach.dima.batch_demo.worker.port.input;

import com.gmail.apach.dima.batch_demo.common.model.JobExecutionInfo;
import com.gmail.apach.dima.batch_demo.common.model.RequestParameters;

public interface ExecuteJobInputPort {

    JobExecutionInfo execute(RequestParameters parameters);
}
