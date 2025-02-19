package com.gmail.apach.dima.batch_demo.application.output.db;

import com.gmail.apach.dima.batch_demo.infrastructure.adapter.output.db.example.entity.WorkExampleEntity;

import java.util.List;

public interface WorkExampleOutputPort {

    void truncate();

    List<WorkExampleEntity> save(List<WorkExampleEntity> workExampleEntities);

    long count();
}
