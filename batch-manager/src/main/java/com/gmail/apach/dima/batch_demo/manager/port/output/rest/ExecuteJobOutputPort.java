package com.gmail.apach.dima.batch_demo.manager.port.output.rest;

import com.gmail.apach.dima.batch_demo.common.model.RequestParameters;
import org.springframework.http.HttpStatus;

public interface ExecuteJobOutputPort {

    HttpStatus execute(RequestParameters parameters);
}
