CREATE TABLE master_table (
    id_         varchar(36),
    column_1    varchar(20)       NOT NULL,
    column_2    integer           NOT NULL,
    column_3    timestamp         NOT NULL,
    column_4    boolean,
    CONSTRAINT master_examples_pkey PRIMARY KEY (id_)
);