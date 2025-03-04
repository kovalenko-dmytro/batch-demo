package com.gmail.apach.dima.batch_demo.port.output.db;

import com.gmail.apach.dima.batch_demo.application.batch.import_csv_to_db.model.MasterModel;

import java.util.List;

public interface MasterTableOutputPort {
    List<MasterModel> save(List<MasterModel> masterModels);

    void delete(List<String> ids);
}
