package com.gmail.apach.dima.batch_demo.application.input;

import com.gmail.apach.dima.batch_demo.core.base.model.job.RequestParameters;

public interface JobExecutionInputPort {

    void execute(RequestParameters parameters);
}
