package com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.job.step.unpack_zip.task;

import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.common.ZippedFileName;
import com.gmail.apach.dima.batch_demo.application.core.common.util.FileUtil;
import com.gmail.apach.dima.batch_demo.application.core.file.model.StoredResource;
import com.gmail.apach.dima.batch_demo.application.core.job.constant.JobExecutionContextKey;
import com.gmail.apach.dima.batch_demo.infrastructure.common.constant.Delimiter;
import com.gmail.apach.dima.batch_demo.infrastructure.common.message.MessageUtil;
import com.gmail.apach.dima.batch_demo.infrastructure.common.message.code.Error;
import lombok.RequiredArgsConstructor;
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
import org.springframework.util.Assert;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class UnpackZipTask implements Tasklet, StepExecutionListener {

    private final MessageUtil messageUtil;

    private StoredResource storedResource;
    private String tempDirPath;

    @Override
    public void beforeStep(@NonNull StepExecution stepExecution) {
        this.storedResource = (StoredResource) stepExecution
            .getJobExecution()
            .getExecutionContext()
            .get(JobExecutionContextKey.STORED_RESOURCE);
    }

    @NonNull
    @Override
    public RepeatStatus execute(
        @NonNull StepContribution contribution,
        @NonNull ChunkContext chunkContext
    ) throws Exception {
        final var tempDirPath = FileUtil.unpackZip(storedResource);

        final var pathsStream = Files.list(Paths.get(tempDirPath));
        final var unpackedFileNames = pathsStream
            .filter(file -> !Files.isDirectory(file))
            .map(Path::getFileName)
            .map(Path::toString)
            .collect(Collectors.toSet());
        pathsStream.close();

        Assert.state(
            unpackedFileNames.containsAll(ZippedFileName.zippedFileNames),
            messageUtil.getMessage(
                Error.FILE_ZIP_CONTENT_MISSING,
                String.join(Delimiter.COMMA, ZippedFileName.zippedFileNames)));

        this.tempDirPath = tempDirPath;
        return RepeatStatus.FINISHED;
    }

    @NonNull
    @Override
    public ExitStatus afterStep(@NonNull StepExecution stepExecution) {
        final var exceptions = stepExecution.getFailureExceptions();
        if (exceptions.isEmpty()) {
            stepExecution.getJobExecution().getExecutionContext()
                .put(JobExecutionContextKey.TEMP_DIR_PATH, tempDirPath);
            return ExitStatus.COMPLETED;
        }
        return ExitStatus.FAILED;
    }
}
