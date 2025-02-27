package com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.job.step.process_template.task;

import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.common.ExportFile;
import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.common.TemplateSheetHeader;
import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.model.TemplateExcelLine;
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
public class TemplateExcelItemWriter
    extends ExcelFileItemWriter<TemplateExcelLine> implements StepExecutionListener {

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
        return TemplateSheetHeader.headers;
    }

    @Override
    protected String getSheetName() {
        return ExportFile.TEMPLATE_SHEET.getValue();
    }

    @Override
    protected String getFilePath() {
        return exportFileTempPath;
    }

    @Override
    protected void fillInCells(TemplateExcelLine item, XSSFRow row) {
        final var nameCell = row.createCell(0, CellType.STRING);
        nameCell.setCellValue(item.name());
        final var descCell = row.createCell(1, CellType.STRING);
        descCell.setCellValue(item.description());
        final var codeCell = row.createCell(2, CellType.NUMERIC);
        codeCell.setCellValue(item.code());
        final var enabledCell = row.createCell(3, CellType.BOOLEAN);
        enabledCell.setCellValue(item.enabled());
    }
}
