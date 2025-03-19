package com.gmail.apach.dima.batch_demo.infrastructure.output.db.work;

import com.gmail.apach.dima.batch_demo.application.batch.import_csv_to_db.model.WorkModel;
import com.gmail.apach.dima.batch_demo.infrastructure.output.db.work.mapper.WorkEntityMapper;
import com.gmail.apach.dima.batch_demo.infrastructure.output.db.work.repository.WorkTableRepository;
import com.gmail.apach.dima.batch_demo.port.output.db.WorkTableOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class WorkTableDbService implements WorkTableOutputPort {

    private final WorkTableRepository workTableRepository;
    private final WorkEntityMapper workEntityMapper;

    @Override
    public void truncate() {
        workTableRepository.truncate();
    }

    @Override
    public void save(List<WorkModel> workModels) {
        final var entities = workModels.stream().map(workEntityMapper::toWorkTableEntity).toList();
        workTableRepository.saveAll(entities);
    }

    @Override
    public long count() {
        return workTableRepository.count();
    }
}
