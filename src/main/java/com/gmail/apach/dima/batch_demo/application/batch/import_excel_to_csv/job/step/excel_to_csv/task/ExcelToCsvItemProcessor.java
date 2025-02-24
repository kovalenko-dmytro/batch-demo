package com.gmail.apach.dima.batch_demo.application.batch.import_excel_to_csv.job.step.excel_to_csv.task;

import com.gmail.apach.dima.batch_demo.application.batch.import_excel_to_csv.model.CsvLine;
import com.gmail.apach.dima.batch_demo.application.batch.import_excel_to_csv.model.ExcelLine;
import com.gmail.apach.dima.batch_demo.infrastructure.common.constant.Delimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class ExcelToCsvItemProcessor implements ItemProcessor<ExcelLine, CsvLine> {

    @NonNull
    @Override
    public CsvLine process(@NonNull ExcelLine excelLine) {
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
