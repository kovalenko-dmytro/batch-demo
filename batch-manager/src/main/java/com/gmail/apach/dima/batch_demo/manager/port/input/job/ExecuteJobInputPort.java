package com.gmail.apach.dima.batch_demo.manager.port.input.job;

import com.gmail.apach.dima.batch_demo.common.model.RequestParameters;
import org.springframework.http.HttpStatus;

public interface ExecuteJobInputPort {

    HttpStatus execute(RequestParameters parameters);
}
