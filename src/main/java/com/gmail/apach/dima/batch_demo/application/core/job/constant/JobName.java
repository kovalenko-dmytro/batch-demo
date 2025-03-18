package com.gmail.apach.dima.batch_demo.application.core.job.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JobName {

    public final static String IMPORT_CSV_TO_DB = "import-csv-to-db";
    public final static String IMPORT_EXCEL_TO_CSV = "import-excel-to-csv";
    public final static String IMPORT_XML_ZIP_TO_EXCEL = "import-xml-zip-to-excel";
}
