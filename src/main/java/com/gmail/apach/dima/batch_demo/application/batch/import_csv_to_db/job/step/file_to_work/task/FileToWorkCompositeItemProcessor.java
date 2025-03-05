package com.gmail.apach.dima.batch_demo.application.batch.import_csv_to_db.job.step.file_to_work.task;

import com.gmail.apach.dima.batch_demo.application.batch.import_csv_to_db.model.CsvLineModel;
import com.gmail.apach.dima.batch_demo.application.batch.import_csv_to_db.model.WorkModel;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.support.CompositeItemProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class FileToWorkCompositeItemProcessor extends CompositeItemProcessor<CsvLineModel, WorkModel> {

    private final FileToWorkValidateItemProcessor validateItemProcessor;
    private final FileToWorkConvertItemProcessor convertItemProcessor;

    @Override
    public void afterPropertiesSet() throws Exception {
        super.setDelegates(List.of(validateItemProcessor, convertItemProcessor));
        super.afterPropertiesSet();
    }
}
