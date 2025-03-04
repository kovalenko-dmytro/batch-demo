package com.gmail.apach.dima.batch_demo.application.batch.import_excel_to_csv.mapper;

import com.gmail.apach.dima.batch_demo.application.batch.import_excel_to_csv.model.CsvLineModel;
import com.gmail.apach.dima.batch_demo.application.batch.import_excel_to_csv.model.ExcelLineModel;
import com.gmail.apach.dima.batch_demo.infrastructure.common.constant.Delimiter;
import jakarta.validation.Valid;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class FileLinesMapper {

    public CsvLineModel toCsvLine(@NonNull @Valid ExcelLineModel excelLineModel) {
        return CsvLineModel.builder()
            .fullName(
                String.join(
                    Delimiter.SPACE,
                    excelLineModel.firstName(),
                    excelLineModel.lastName()))
            .age(excelLineModel.age())
            .enabled(excelLineModel.active())
            .build();
    }
}
