package com.gmail.apach.dima.batch_demo.infrastructure.adapter.output.db.inventory.repository;

import com.gmail.apach.dima.batch_demo.infrastructure.adapter.output.db.inventory.entity.ExampleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExampleRepository extends JpaRepository<ExampleEntity, String> {
}
