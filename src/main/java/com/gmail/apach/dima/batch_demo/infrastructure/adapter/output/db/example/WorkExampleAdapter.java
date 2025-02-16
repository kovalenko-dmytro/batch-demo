package com.gmail.apach.dima.batch_demo.infrastructure.adapter.output.db.example;

import com.gmail.apach.dima.batch_demo.application.output.db.WorkExampleOutputPort;
import com.gmail.apach.dima.batch_demo.infrastructure.adapter.output.db.example.repository.WorkExampleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkExampleAdapter implements WorkExampleOutputPort {

    private final WorkExampleRepository workExampleRepository;
    @Override
    public void truncate() {
        workExampleRepository.deleteAll();
    }
}
