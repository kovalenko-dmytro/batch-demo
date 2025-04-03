package com.gmail.apach.dima.batch_demo.manager.port.input.job;

import com.gmail.apach.dima.batch_demo.manager.application.job.model.RegisteredJob;

import java.util.List;

public interface GetRegisteredJobsInputPort {

    List<RegisteredJob> getAll();
}
