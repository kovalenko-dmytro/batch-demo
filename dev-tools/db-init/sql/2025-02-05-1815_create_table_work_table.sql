CREATE TABLE work_table (
    id_              varchar(36),
    field_param_1    varchar(20)       NOT NULL,
    field_param_2    integer           NOT NULL,
    field_param_3    timestamp         NOT NULL,
    field_param_4    boolean,
    CONSTRAINT work_examples_pkey PRIMARY KEY (id_)
);