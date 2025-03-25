# Batch demo application


## Description

Batch demo application provides features that are responsible in processing large volumes of records, including logging, 
transaction management, job processing, error handling and resource management. 

Features

* Transaction management
* Chunk based processing
* Ability to process different files - **csv**, **excel**, **xm**l both input and output
* Ability to read/write process from/to database 

## Application architecture

<ins>**Hexagonal architecture**</ins>

## Technology stack

* Spring Batch.
* Spring Web.
* Spring Shell.
* Spring Data.
* Spring Validation.
* Spring cloud AWS.
* Spring doc Open Api.
* Liquibase.
* PostgreSQL.
* Mapstruct.

## Supported startup mode profiles

The application supports the following startup modes:

* **web** - allow to receive HTTP Web requests
* **cli**  - allow to run application one-time and execute a particular job via command line args
* **shell** - allow to execute different jobs many times in command line using shell cli

## Supported environment profiles

The application supports the following env profiles:

* **local**
* **development**
* **staging**
* **production**

## Run application

### Demo files
Demo import files are located in:  
**\<application-directory-destination\>/demo-files**  
These files can be uploaded to local object storage using running application under 
profiles **local** and **web** and calling REST API: 
**POST http://<i></i>localhost:${SERVER_PORT}/${SERVER_CONTEXT_PATH}/api/v1/files**  

**Jobs -> Files**:  
* import-csv-to-db -> import_csv_to_db.csv
* import-excel-to-csv -> import_excel_to_csv.xlsx
* import-xml-zip-to-excel -> import_xml_zip_to_excel.zip


### Active profile \<local\>
1. Create a file **.env** under **dev-tools** directory using example **dev-tools/.env-example**
2. Fill in  **.env** file using your preferred configurations
3. Run your Docker (for windows: Docker Desktop)
4. Run command **\<application-directory-destination\>/dev-tools** **docker compose up**
5. Set up active profiles as environment variable **APPLICATION_PROFILE**=**local,[web/cli/shell]**   
6. Launch the application using main class

#### - Startup mode \<web\>
Open api documentation will be available at:  
**http://<i></i>localhost:${SERVER_PORT}/${SERVER_CONTEXT_PATH}/swagger-ui/index.html**  

To upload source file for batch processing:  
POST http://<i></i>localhost:${SERVER_PORT}/${SERVER_CONTEXT_PATH}/api/v1/files

To see available jobs:  
GET http://<i></i>localhost:${SERVER_PORT}/${SERVER_CONTEXT_PATH}/api/v1/registered-jobs

To execute the batch:  
POST http://<i></i>localhost:${SERVER_PORT}/${SERVER_CONTEXT_PATH}/api/v1/batches

To control executed batch status:  
GET http://<i></i>localhost:${SERVER_PORT}/${SERVER_CONTEXT_PATH}/api/v1/batches/{job-execution-marker}

#### - Startup mode \<cli\>
**Program arguments:**  
job-name=specify  
job-execution-marker=specify  
file-storage-resource=specify  

Add needed program args before running application, example:  
**job-name=import-csv-to-db file-storage-resource=import_csv_to_db.csv**

#### - Startup mode \<shell\>
Shell command to execute batch: **execute-job** 

**Shell option arguments:**  
--job-name specify  
--job-execution-marker specify  
--file-storage-resource specify  

Input  shell command to execute required job, example:  
**execute-job --job-name import-csv-to-db --file-storage-resource import_csv_to_db.csv**
