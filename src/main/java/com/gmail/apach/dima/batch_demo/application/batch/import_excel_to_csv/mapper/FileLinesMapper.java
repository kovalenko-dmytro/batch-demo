package com.gmail.apach.dima.batch_demo.application.batch.import_excel_to_csv.mapper;

import com.gmail.apach.dima.batch_demo.application.batch.import_excel_to_csv.model.CsvLine;
import com.gmail.apach.dima.batch_demo.application.batch.import_excel_to_csv.model.ExcelLine;
import com.gmail.apach.dima.batch_demo.infrastructure.common.constant.Delimiter;
import jakarta.validation.Valid;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class FileLinesMapper {

    public CsvLine toCsvLine(@NonNull @Valid ExcelLine excelLine) {
        return CsvLine.builder()
            .fullName(
                String.join(
                    Delimiter.SPACE,
                    excelLine.firstName(),
                    excelLine.lastName()))
            .age(excelLine.age())
            .enabled(excelLine.active())
            .build();
    }
}
