package com.gmail.apach.dima.batch_demo.port.output.db;

import com.gmail.apach.dima.batch_demo.infrastructure.adapter.output.db.master.entity.MasterTableEntity;

import java.util.List;

public interface MasterTableOutputPort {
    List<MasterTableEntity> save(List<MasterTableEntity> masterTableEntities);

    void delete(List<String> ids);
}
