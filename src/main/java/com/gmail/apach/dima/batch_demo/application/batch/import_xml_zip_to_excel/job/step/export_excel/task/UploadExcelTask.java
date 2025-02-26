package com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.job.step.export_excel.task;

import com.gmail.apach.dima.batch_demo.application.core.common.util.FileUtil;
import com.gmail.apach.dima.batch_demo.application.core.job.constant.JobExecutionContextKey;
import com.gmail.apach.dima.batch_demo.infrastructure.common.message.MessageUtil;
import com.gmail.apach.dima.batch_demo.infrastructure.common.message.code.Info;
import com.gmail.apach.dima.batch_demo.port.output.oss.AwsS3OutputPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

import java.io.File;

@Slf4j
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class UploadExcelTask implements Tasklet, StepExecutionListener {

    private final AwsS3OutputPort awsS3OutputPort;
    private final MessageUtil messageUtil;

    private String exportFileTempPath;

    @Override
    public void beforeStep(@NonNull StepExecution stepExecution) {
        this.exportFileTempPath = (String) stepExecution
            .getJobExecution()
            .getExecutionContext()
            .get(JobExecutionContextKey.EXPORT_FILE_TEMP_PATH);
    }

    @NonNull
    @Override
    public RepeatStatus execute(
        @NonNull StepContribution contribution,
        @NonNull ChunkContext chunkContext
    ) {
        final var storedResource = awsS3OutputPort.save(new File(exportFileTempPath));
        log.info(messageUtil.getMessage(Info.JOB_STEP_EXPORT_FILE_UPLOADED, storedResource.getStorageKey()));
        return RepeatStatus.FINISHED;
    }

    @NonNull
    @Override
    public ExitStatus afterStep(@NonNull StepExecution stepExecution) {
        final var exceptions = stepExecution.getFailureExceptions();
        FileUtil.deleteTempFile(exportFileTempPath);
        return exceptions.isEmpty() ? ExitStatus.COMPLETED : ExitStatus.FAILED;
    }
}
