package com.gmail.apach.dima.batch_demo.domain.repository;

import com.gmail.apach.dima.batch_demo.domain.entity.ExampleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExampleRepository extends JpaRepository<ExampleEntity, String> {
}
