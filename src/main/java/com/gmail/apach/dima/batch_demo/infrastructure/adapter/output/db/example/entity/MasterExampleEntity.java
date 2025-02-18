package com.gmail.apach.dima.batch_demo.infrastructure.adapter.output.db.example.entity;

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
@Table(name = "master_examples")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class MasterExampleEntity {

    @Id
    @UuidGenerator
    @Column(name = "id_", length = 36, unique = true, nullable = false)
    private String id;

    @Column(name = "column_1", nullable = false)
    String column1;

    @Column(name = "column_2", nullable = false)
    Integer column2;

    @Column(name = "column_3", nullable = false)
    LocalDateTime column3;

    @Column(name = "column_4")
    Boolean column4;
}
