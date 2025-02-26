package com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.job.step.create_excel.task;

import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.common.ExportFile;
import com.gmail.apach.dima.batch_demo.application.core.common.util.FileUtil;
import com.gmail.apach.dima.batch_demo.application.core.job.constant.JobExecutionContextKey;
import com.gmail.apach.dima.batch_demo.infrastructure.common.constant.Extension;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;

@Slf4j
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class CreateExcelTask implements Tasklet, StepExecutionListener {

    private String exportFileTempPath;

    @NonNull
    @Override
    public RepeatStatus execute(
        @NonNull StepContribution contribution,
        @NonNull ChunkContext chunkContext
    ) throws Exception {
        final var workBook = new XSSFWorkbook();
        ExportFile.sheetNames().forEach(workBook::createSheet);

        this.exportFileTempPath = FileUtil.createTempFile(ExportFile.FILE_NAME.getValue(), Extension.EXCEL);
        final var fos = new FileOutputStream(exportFileTempPath);
        workBook.write(fos);

        workBook.close();
        fos.close();

        return RepeatStatus.FINISHED;
    }

    @NonNull
    @Override
    public ExitStatus afterStep(@NonNull StepExecution stepExecution) {
        final var exceptions = stepExecution.getFailureExceptions();
        if (exceptions.isEmpty()) {
            stepExecution
                .getJobExecution()
                .getExecutionContext()
                .put(JobExecutionContextKey.EXPORT_FILE_TEMP_PATH, exportFileTempPath);
            return ExitStatus.COMPLETED;
        }
        return ExitStatus.FAILED;
    }
}
