package com.gmail.apach.dima.batch_demo.infrastructure.adapter.output.db.inventory.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;

@Entity
@Table(name = "examples")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ExampleEntity {

    @Id
    @UuidGenerator
    @Column(name = "id_", length = 36, unique = true, nullable = false)
    private String id;

    @Column(name = "string_column", nullable = false)
    String stringColumn;

    @Column(name = "integer_column", nullable = false)
    Integer integerColumn;

    @Column(name = "datetime_column", nullable = false)
    LocalDateTime dateTimeColumn;

    @Column(name = "boolean_column")
    Boolean booleanColumn;
}
