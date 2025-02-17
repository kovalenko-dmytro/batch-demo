package com.gmail.apach.dima.batch_demo.application.output.db;

import com.gmail.apach.dima.batch_demo.infrastructure.adapter.output.db.example.entity.MasterExampleEntity;

import java.util.List;

public interface MasterExampleOutputPort {

    List<MasterExampleEntity> save(List<MasterExampleEntity> exampleEntities);

    void delete(List<String> ids);
}
