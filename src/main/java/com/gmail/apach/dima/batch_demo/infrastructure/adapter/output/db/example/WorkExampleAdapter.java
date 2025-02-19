package com.gmail.apach.dima.batch_demo.infrastructure.adapter.output.db.example;

import com.gmail.apach.dima.batch_demo.application.output.db.WorkExampleOutputPort;
import com.gmail.apach.dima.batch_demo.infrastructure.adapter.output.db.example.entity.WorkExampleEntity;
import com.gmail.apach.dima.batch_demo.infrastructure.adapter.output.db.example.repository.WorkExampleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class WorkExampleAdapter implements WorkExampleOutputPort {

    private final WorkExampleRepository workExampleRepository;

    @Override
    public void truncate() {
        workExampleRepository.deleteAll();
    }

    @Override
    public List<WorkExampleEntity> save(List<WorkExampleEntity> workExampleEntities) {
        return workExampleRepository.saveAll(workExampleEntities);
    }

    @Override
    public long count() {
        return workExampleRepository.count();
    }
}
