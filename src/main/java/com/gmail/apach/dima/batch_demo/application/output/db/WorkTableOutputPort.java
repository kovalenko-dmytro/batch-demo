package com.gmail.apach.dima.batch_demo.application.output.db;

import com.gmail.apach.dima.batch_demo.infrastructure.adapter.output.db.work.entity.WorkTableEntity;

import java.util.List;

public interface WorkTableOutputPort {

    void truncate();

    void save(List<WorkTableEntity> workTableEntities);

    long count();
}
