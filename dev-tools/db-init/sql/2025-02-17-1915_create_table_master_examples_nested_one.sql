CREATE TABLE master_examples_nested_one (
    id_         varchar(36),
    parent_     varchar(36)       NOT NULL,
    column_1    varchar(50)       NOT NULL,
    column_2    varchar(2)        NOT NULL,
    column_3    integer           NOT NULL,
    CONSTRAINT master_examples_nested_one_pkey PRIMARY KEY (id_),
    CONSTRAINT master_examples_nested_one_fkey FOREIGN KEY (parent_) REFERENCES master_examples (id_) ON DELETE CASCADE
);