package com.gmail.apach.dima.batch_demo.infrastructure.adapter.output.db.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "master_examples_nested_one")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class MasterExampleNestedOneEntity {

    @Id
    @UuidGenerator
    @Column(name = "id_", length = 36, unique = true, nullable = false)
    private String id;

    @Column(name = "column_1", nullable = false)
    String column1;

    @Column(name = "column_2", nullable = false)
    String column2;

    @Column(name = "column_3", nullable = false)
    Integer column3;

    @OneToOne
    @JoinColumn(name = "parent_")
    private MasterExampleEntity parent;
}
