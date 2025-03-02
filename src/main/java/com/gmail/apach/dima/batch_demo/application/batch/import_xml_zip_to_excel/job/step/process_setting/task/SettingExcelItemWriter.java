package com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.job.step.process_setting.task;

import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.common.ExportFile;
import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.common.SettingSheetHeader;
import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.model.SettingExcelLineModel;
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
public class SettingExcelItemWriter
    extends ExcelFileItemWriter<SettingExcelLineModel> implements StepExecutionListener {

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
        return SettingSheetHeader.headers;
    }

    @Override
    protected String getSheetName() {
        return ExportFile.SETTING_SHEET.getValue();
    }

    @Override
    protected String getFilePath() {
        return exportFileTempPath;
    }

    @Override
    protected void fillInCells(SettingExcelLineModel item, XSSFRow row) {
        final var activeFlagCell = row.createCell(0, CellType.BOOLEAN);
        activeFlagCell.setCellValue(item.activeFlag());
        final var approveFlagCell = row.createCell(1, CellType.BOOLEAN);
        approveFlagCell.setCellValue(item.approveFlag());
    }
}
