package com.gmail.apach.dima.batch_demo.infrastructure.adapter.output.db.work.entity;

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
@Table(name = "work_table")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class WorkTableEntity {

    @Id
    @UuidGenerator
    @Column(name = "id_", length = 36, unique = true, nullable = false)
    private String id;

    @Column(name = "field_param_1", nullable = false)
    String fieldParam1;

    @Column(name = "field_param_2", nullable = false)
    Integer fieldParam2;

    @Column(name = "field_param_3", nullable = false)
    LocalDateTime fieldParam3;

    @Column(name = "field_param_4")
    Boolean fieldParam4;
}
