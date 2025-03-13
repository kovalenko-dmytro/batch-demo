package com.gmail.apach.dima.batch_demo.infrastructure.output.db.master;

import com.gmail.apach.dima.batch_demo.application.batch.import_csv_to_db.model.MasterModel;
import com.gmail.apach.dima.batch_demo.infrastructure.output.db.master.mapper.MasterEntityMapper;
import com.gmail.apach.dima.batch_demo.infrastructure.output.db.master.repository.MasterTableRepository;
import com.gmail.apach.dima.batch_demo.port.output.db.MasterTableOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MasterTableAdapter implements MasterTableOutputPort {

    private final MasterTableRepository masterTableRepository;
    private final MasterEntityMapper masterEntityMapper;

    @Override
    public List<MasterModel> save(List<MasterModel> masterModels) {
        final var inputEntities = masterModels.stream().map(masterEntityMapper::toMasterTableEntity).toList();
        final var savedEntities = masterTableRepository.saveAll(inputEntities);
        return savedEntities.stream().map(masterEntityMapper::toMasterModel).toList();
    }

    @Override
    public void delete(List<String> ids) {
        masterTableRepository.deleteAllById(ids);
    }
}
