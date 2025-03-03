# Batch demo application


## Description

Batch demo application provides features that are responsible in processing large volumes of records, including logging, 
transaction management, job processing, error handling and resource management. 

Features

* Transaction management
* Chunk based processing
* Ability to process different files - **csv**, **excel**, **xm**l both input and output
* Ability to read/write process from/ti database 

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
### <ins>**local**</ins> profile
1. Create a file **.env** under **dev-tools** directory using example **dev-tools/.env-example**
2. Fill in  **.env** file using your preferred configurations
3. Run your Docker (wor windows: Docker Desktop)
4. Run command **<application-directory-destination>\dev-tools>** **docker compose up**
5. Set in your IDE env variable **APPLICATION_PROFILE**=**local,[web/cli/shell]**
   - **web** - web startup mode - open api will be available at **http://<i></i>localhost:${SERVER_PORT}/${SERVER_CONTEXT_PATH}/swagger-ui/index.html**
   - **cli** - cli startup mode - add needed program args, example: **job-name=import-csv-to-db file-storage-resource=example.csv**
   - **shell** - shell startup mode - after application running input command, example: **execute-job --job-name import-csv-to-db --file-storage-resource example.csv**
6. Launch the application using main class

