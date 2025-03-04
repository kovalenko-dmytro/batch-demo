package com.gmail.apach.dima.batch_demo.port.output.db;

import com.gmail.apach.dima.batch_demo.application.batch.import_csv_to_db.model.WorkModel;

import java.util.List;

public interface WorkTableOutputPort {
    void truncate();

    void save(List<WorkModel> workModels);

    long count();
}
