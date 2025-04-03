package com.gmail.apach.dima.batch_demo.manager.port.output.db;

import com.gmail.apach.dima.batch_demo.manager.application.job.model.RegisteredJob;

import java.util.List;

public interface GetRegisteredJobsOutputPort {

    List<RegisteredJob> getAll();
}
