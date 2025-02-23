package com.gmail.apach.dima.batch_demo.infrastructure.adapter.output.db.master;

import com.gmail.apach.dima.batch_demo.infrastructure.adapter.output.db.master.entity.MasterTableEntity;
import com.gmail.apach.dima.batch_demo.infrastructure.adapter.output.db.master.repository.MasterTableRepository;
import com.gmail.apach.dima.batch_demo.port.output.db.MasterTableOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MasterTableAdapter implements MasterTableOutputPort {

    private final MasterTableRepository masterTableRepository;

    @Override
    public List<MasterTableEntity> save(List<MasterTableEntity> masterTableEntities) {
        return masterTableRepository.saveAll(masterTableEntities);
    }

    @Override
    public void delete(List<String> ids) {
        masterTableRepository.deleteAllById(ids);
    }
}
