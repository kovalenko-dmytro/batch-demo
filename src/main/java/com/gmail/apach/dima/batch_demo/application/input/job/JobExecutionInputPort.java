package com.gmail.apach.dima.batch_demo.application.input.job;

import com.gmail.apach.dima.batch_demo.core.base.job.model.RequestParameters;

public interface JobExecutionInputPort {

    void execute(RequestParameters parameters);
}
