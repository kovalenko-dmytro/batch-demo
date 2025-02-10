CREATE TABLE examples (
    id_              varchar(36),
    string_column    varchar(10)       NOT NULL,
    integer_column   integer           NOT NULL,
    datetime_column  timestamp         NOT NULL,
    boolean_column   boolean,
    CONSTRAINT examples_pkey PRIMARY KEY (id_)
);