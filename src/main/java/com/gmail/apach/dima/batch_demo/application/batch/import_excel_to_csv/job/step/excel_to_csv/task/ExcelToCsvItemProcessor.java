package com.gmail.apach.dima.batch_demo.application.batch.import_excel_to_csv.job.step.excel_to_csv.task;

import com.gmail.apach.dima.batch_demo.application.batch.import_excel_to_csv.mapper.FileLinesMapper;
import com.gmail.apach.dima.batch_demo.application.batch.import_excel_to_csv.model.CsvLineModel;
import com.gmail.apach.dima.batch_demo.application.batch.import_excel_to_csv.model.ExcelLineModel;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class ExcelToCsvItemProcessor implements ItemProcessor<ExcelLineModel, CsvLineModel> {

    private final FileLinesMapper fileLinesMapper;

    @NonNull
    @Override
    public CsvLineModel process(@NonNull ExcelLineModel excelLineModel) {
        return fileLinesMapper.toCsvLine(excelLineModel);
    }
}
