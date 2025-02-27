package com.gmail.apach.dima.batch_demo.application.core.job.writer;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.lang.NonNull;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public abstract class ExcelFileItemWriter<T> implements ItemWriter<T> {

    @Override
    @SuppressWarnings("unchecked")
    public void write(@NonNull Chunk<? extends T> chunk) throws Exception {
        final var workBook = readFile();
        final var currentSheet = workBook.getSheet(getSheetName());
        setHeaders(currentSheet);
        fillInSheet((List<T>) chunk.getItems(), currentSheet);
        writeFile(workBook);
    }

    private void fillInSheet(List<T> items, XSSFSheet currentSheet) {
        var lastRowNum = currentSheet.getLastRowNum();
        for (var item : items) {
            final var row = currentSheet.createRow(++lastRowNum);
            fillInCells(item, row);
        }
    }

    private XSSFWorkbook readFile() throws IOException {
        final var fis = new FileInputStream(getFilePath());
        final var workBook = new XSSFWorkbook(fis);
        fis.close();
        return workBook;
    }

    private void writeFile(XSSFWorkbook workBook) throws IOException {
        final var fos = new FileOutputStream(getFilePath());
        workBook.write(fos);
        workBook.close();
        fos.close();
    }

    private void setHeaders(XSSFSheet currentSheet) {
        var lastRowNum = currentSheet.getLastRowNum();
        if (currentSheet.getLastRowNum() == -1 && currentSheet.getRow(0) == null) {
            final var headersRow = currentSheet.createRow(++lastRowNum);
            var cellNum = 0;
            for (var header : getSheetHeaders()) {
                var cell = headersRow.createCell(cellNum++, CellType.STRING);
                cell.setCellValue(header);
            }
        }
    }

    protected abstract List<String> getSheetHeaders();

    protected abstract String getSheetName();

    protected abstract String getFilePath();

    protected abstract void fillInCells(T item, XSSFRow row);
}
