package com.gmail.apach.dima.batch_demo.infrastructure.output.db.work.repository;

import com.gmail.apach.dima.batch_demo.infrastructure.output.db.work.entity.WorkTableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkTableRepository extends JpaRepository<WorkTableEntity, String> {

    @Modifying
    @Query(value = "TRUNCATE TABLE work_table", nativeQuery = true)
    void truncate();
}
