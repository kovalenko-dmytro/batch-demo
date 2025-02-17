CREATE TABLE master_examples_nested_two (
    id_         varchar(36),
    parent_     varchar(36)       NOT NULL,
    column_1    varchar(10)       NOT NULL,
    column_2    integer           NOT NULL,
    CONSTRAINT master_examples_nested_two_pkey PRIMARY KEY (id_),
    CONSTRAINT master_examples_nested_two_fkey FOREIGN KEY (parent_) REFERENCES master_examples (id_) ON DELETE CASCADE
);