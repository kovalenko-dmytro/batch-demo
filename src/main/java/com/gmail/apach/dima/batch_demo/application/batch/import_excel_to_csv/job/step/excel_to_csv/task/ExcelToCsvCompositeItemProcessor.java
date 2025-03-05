package com.gmail.apach.dima.batch_demo.application.batch.import_excel_to_csv.job.step.excel_to_csv.task;

import com.gmail.apach.dima.batch_demo.application.batch.import_excel_to_csv.model.CsvLineModel;
import com.gmail.apach.dima.batch_demo.application.batch.import_excel_to_csv.model.ExcelLineModel;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.support.CompositeItemProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class ExcelToCsvCompositeItemProcessor extends CompositeItemProcessor<ExcelLineModel, CsvLineModel> {

    private final ExcelToCsvValidateItemProcessor validateItemProcessor;
    private final ExcelToCsvConvertItemProcessor convertItemProcessor;

    @Override
    public void afterPropertiesSet() throws Exception {
        super.setDelegates(List.of(validateItemProcessor, convertItemProcessor));
        super.afterPropertiesSet();
    }
}
