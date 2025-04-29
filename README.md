# Batch demo application


## Description

Batch demo application provides features that are responsible in processing large volumes of records, including logging, 
transaction management, job processing, error handling and resource management. 

Features

* Transaction management
* Chunk based processing
* Ability to process different files - **csv**, **excel**, **xml** both input and output
* Ability to read/write process from/to database  
* Ability to upload/download files using object storage service (AWS S3 etc.)

## Application architecture

<ins>**Hexagonal architecture**</ins>  

Includes 3th modules:  
* batch-common  
* batch-manager  
* batch-worker  

## Technology stack

* Spring Batch.
* Spring Web.
* Spring Data.
* Spring Validation.
* Spring cloud AWS.
* Spring doc Open Api.
* Liquibase.
* PostgreSQL.
* Mapstruct.

## Run application

### Demo files
Demo import files are located in:  
**\<application-directory-destination\>/batch-manager/demo-files**  
These files can be uploaded to local object storage using running application **batch-manager** and calling REST API: 
**POST http://<i></i>localhost:${BATCH_MANAGER_PORT}/${BATCH_MANAGER_CONTEXT_PATH}/api/v1/files**  

**Jobs -> Files**:  
* import-csv-to-db -> import_csv_to_db.csv
* import-excel-to-csv -> import_excel_to_csv.xlsx
* import-xml-zip-to-excel -> import_xml_zip_to_excel.zip


### Active profile \<local\>
1. Fill in  **\<application-directory-destination\>/dev-tools/.env** file with your preferred configurations
2. Run your Docker (for Windows: Docker Desktop)  
3. Launch applications **batch-manager** and **batch-worker**  using environment variable **APPLICATION_PROFILE**=**local**  

#### <ins>Application \<batch-manager\></ins>
Application provides batch execution management.  

Open api documentation will be available at:  
**http://<i></i>localhost:${BATCH_MANAGER_PORT}/${BATCH_MANAGER_CONTEXT_PATH}/swagger-ui/index.html**  

To upload source file for batch processing:  
POST /api/v1/files

To see available jobs:  
GET /api/v1/registered-jobs

To execute the batch:  
POST /api/v1/batches

To control executed batch status:  
GET /api/v1/batches/{job-execution-marker}

#### <ins>Application \<batch-worker\></ins>
Application includes batch core logic and particular batches.  

Open api documentation will be available at:  
**http://<i></i>localhost:${BATCH_WORKER_PORT}/${BATCH_WORKER_CONTEXT_PATH}/swagger-ui/index.html**

To execute the batch:  
POST /api/v1/batches