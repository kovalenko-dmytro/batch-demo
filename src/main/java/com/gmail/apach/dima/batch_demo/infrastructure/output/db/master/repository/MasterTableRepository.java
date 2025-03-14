package com.gmail.apach.dima.batch_demo.infrastructure.output.db.master.repository;

import com.gmail.apach.dima.batch_demo.infrastructure.output.db.master.entity.MasterTableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterTableRepository extends JpaRepository<MasterTableEntity, String> {
}
