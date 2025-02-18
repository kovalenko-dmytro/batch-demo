package com.gmail.apach.dima.batch_demo.infrastructure.adapter.output.db.example;

import com.gmail.apach.dima.batch_demo.application.output.db.MasterExampleOutputPort;
import com.gmail.apach.dima.batch_demo.infrastructure.adapter.output.db.example.entity.MasterExampleEntity;
import com.gmail.apach.dima.batch_demo.infrastructure.adapter.output.db.example.repository.MasterExampleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MasterExampleAdapter implements MasterExampleOutputPort {

    private final MasterExampleRepository masterExampleRepository;

    @Override
    public List<MasterExampleEntity> save(List<MasterExampleEntity> exampleEntities) {
        return masterExampleRepository.saveAll(exampleEntities);
    }

    @Override
    public void delete(List<String> ids) {
        masterExampleRepository.deleteAllById(ids);
    }
}
