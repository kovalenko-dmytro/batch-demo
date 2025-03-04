package com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.job.step.proccess_config.task;

import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.common.ConfigSheetHeader;
import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.common.ExportFile;
import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.model.ConfigExcelLineModel;
import com.gmail.apach.dima.batch_demo.application.core.job.constant.JobExecutionContextKey;
import com.gmail.apach.dima.batch_demo.application.core.job.writer.ExcelFileItemWriter;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class ConfigExcelItemWriter
    extends ExcelFileItemWriter<ConfigExcelLineModel> implements StepExecutionListener {

    private String exportFileTempPath;

    @Override
    public void beforeStep(@NonNull StepExecution stepExecution) {
        this.exportFileTempPath = (String) stepExecution
            .getJobExecution()
            .getExecutionContext()
            .get(JobExecutionContextKey.EXPORT_FILE_TEMP_PATH);
    }

    @Override
    protected List<String> getSheetHeaders() {
        return ConfigSheetHeader.headers;
    }

    @Override
    protected String getSheetName() {
        return ExportFile.CONFIG_SHEET.getValue();
    }

    @Override
    protected String getFilePath() {
        return exportFileTempPath;
    }

    @Override
    protected void fillInCells(ConfigExcelLineModel item, XSSFRow row) {
        final var localeCell = row.createCell(0, CellType.STRING);
        localeCell.setCellValue(item.locale());
        final var versionCell = row.createCell(1, CellType.STRING);
        versionCell.setCellValue(item.version());
    }
}
