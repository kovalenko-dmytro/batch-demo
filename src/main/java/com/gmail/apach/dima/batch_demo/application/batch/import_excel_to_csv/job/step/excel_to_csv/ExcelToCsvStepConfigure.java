package com.gmail.apach.dima.batch_demo.application.batch.import_excel_to_csv.job.step.excel_to_csv;

import com.gmail.apach.dima.batch_demo.application.batch.import_excel_to_csv.common.ImportExcelToCsvStep;
import com.gmail.apach.dima.batch_demo.application.batch.import_excel_to_csv.job.step.excel_to_csv.task.CsvItemWriter;
import com.gmail.apach.dima.batch_demo.application.batch.import_excel_to_csv.job.step.excel_to_csv.task.ExcelItemReader;
import com.gmail.apach.dima.batch_demo.application.batch.import_excel_to_csv.job.step.excel_to_csv.task.ExcelToCsvCompositeItemProcessor;
import com.gmail.apach.dima.batch_demo.application.batch.import_excel_to_csv.model.CsvLineModel;
import com.gmail.apach.dima.batch_demo.application.batch.import_excel_to_csv.model.ExcelLineModel;
import com.gmail.apach.dima.batch_demo.application.core.job.configure.BaseBatchConfigure;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ExcelToCsvStepConfigure {

    private final BaseBatchConfigure configure;
    private final ExcelItemReader excelItemReader;
    private final ExcelToCsvCompositeItemProcessor compositeItemProcessor;
    private final CsvItemWriter csvItemWriter;

    @Bean
    @SuppressWarnings("unused")
    protected Step excelToCsvStep() {
        return new StepBuilder(ImportExcelToCsvStep.EXCEL_TO_CSV_STEP.getName(), configure.getJobRepository())
            .<ExcelLineModel, CsvLineModel>chunk(
                configure.getBatchConfigProperties().getBatchSize(),
                configure.getPlatformTransactionManager()
            )
            .reader(excelItemReader)
            .processor(compositeItemProcessor)
            .writer(csvItemWriter)
            .listener(configure.getLogStepExecutionListener())
            .exceptionHandler(configure.getBaseJobExceptionHandler())
            .build();
    }
}
