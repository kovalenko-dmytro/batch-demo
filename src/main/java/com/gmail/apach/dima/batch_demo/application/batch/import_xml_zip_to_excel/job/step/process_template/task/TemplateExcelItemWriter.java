package com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.job.step.process_template.task;

import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.common.ExportFile;
import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.common.TemplateSheetHeader;
import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.model.TemplateExcelLine;
import com.gmail.apach.dima.batch_demo.application.core.job.constant.JobExecutionContextKey;
import lombok.RequiredArgsConstructor;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class TemplateExcelItemWriter implements ItemWriter<TemplateExcelLine>, StepExecutionListener {

    private String exportFileTempPath;

    @Override
    public void beforeStep(@NonNull StepExecution stepExecution) {
        this.exportFileTempPath = (String) stepExecution
            .getJobExecution()
            .getExecutionContext()
            .get(JobExecutionContextKey.EXPORT_FILE_TEMP_PATH);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void write(@NonNull Chunk<? extends TemplateExcelLine> chunk) throws Exception {
        final var fis = new FileInputStream(exportFileTempPath);
        final var workBook = new HSSFWorkbook(fis);
        fis.close();

        final var currentSheet = workBook.getSheet(ExportFile.TEMPLATE_SHEET.getValue());
        var lastRowNum = currentSheet.getLastRowNum();

        if (currentSheet.getLastRowNum() == 0 && currentSheet.getRow(0) == null) {
            final var headersRow = currentSheet.createRow(++lastRowNum);
            var cellNum = 0;
            for (var header : TemplateSheetHeader.headers) {
                var cell = headersRow.createCell(cellNum++, CellType.STRING);
                cell.setCellValue(header);
            }
        }

        final var excelLines = (List<TemplateExcelLine>) chunk.getItems();
        for (var excelLine : excelLines) {
            final var row = currentSheet.createRow(++lastRowNum);
            var nameCell = row.createCell(0, CellType.STRING);
            nameCell.setCellValue(excelLine.name());
            var descCell = row.createCell(1, CellType.STRING);
            descCell.setCellValue(excelLine.description());
            var codeCell = row.createCell(2, CellType.NUMERIC);
            codeCell.setCellValue(excelLine.code());
            var enabledCell = row.createCell(3, CellType.BOOLEAN);
            enabledCell.setCellValue(excelLine.enabled());
        }

        final var fos = new FileOutputStream(exportFileTempPath);
        workBook.write(fos);
        workBook.close();
        fos.close();
    }
}
