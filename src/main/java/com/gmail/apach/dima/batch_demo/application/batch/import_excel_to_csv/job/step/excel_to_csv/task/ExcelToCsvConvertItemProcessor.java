package com.gmail.apach.dima.batch_demo.application.batch.import_excel_to_csv.job.step.excel_to_csv.task;

import com.gmail.apach.dima.batch_demo.application.batch.import_excel_to_csv.model.CsvLineModel;
import com.gmail.apach.dima.batch_demo.application.batch.import_excel_to_csv.model.ExcelLineModel;
import com.gmail.apach.dima.batch_demo.infrastructure.common.constant.Delimiter;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ExcelToCsvConvertItemProcessor implements ItemProcessor<ExcelLineModel, CsvLineModel> {

    @NonNull
    @Override
    public CsvLineModel process(@NonNull ExcelLineModel item) {
        return CsvLineModel.builder()
            .fullName(
                String.join(
                    Delimiter.SPACE,
                    item.firstName(),
                    item.lastName()))
            .age(item.age())
            .enabled(item.active())
            .build();
    }
}
