CREATE TABLE work_examples (
    id_              varchar(36),
    field_param_1    varchar(20)       NOT NULL,
    field_param_2    integer           NOT NULL,
    field_param_3    timestamp         NOT NULL,
    field_param_4    boolean,
    field_param_5    varchar(50)       NOT NULL,
    field_param_6    varchar(2)        NOT NULL,
    field_param_7    integer           NOT NULL,
    field_param_8    varchar(10)       NOT NULL,
    field_param_9    integer           NOT NULL,
    CONSTRAINT work_examples_pkey PRIMARY KEY (id_)
);