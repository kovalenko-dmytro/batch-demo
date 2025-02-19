package com.gmail.apach.dima.batch_demo.infrastructure.adapter.output.db.work;

import com.gmail.apach.dima.batch_demo.application.output.db.WorkTableOutputPort;
import com.gmail.apach.dima.batch_demo.infrastructure.adapter.output.db.work.entity.WorkTableEntity;
import com.gmail.apach.dima.batch_demo.infrastructure.adapter.output.db.work.repository.WorkTableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class WorkTableAdapter implements WorkTableOutputPort {

    private final WorkTableRepository workTableRepository;

    @Override
    public void truncate() {
        workTableRepository.deleteAll();
    }

    @Override
    public void save(List<WorkTableEntity> workTableEntities) {
        workTableRepository.saveAll(workTableEntities);
    }

    @Override
    public long count() {
        return workTableRepository.count();
    }
}
