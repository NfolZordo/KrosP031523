package com.ars.manager.repository;

import com.ars.manager.domain.CustomMigration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomMigrationRepository extends JpaRepository<CustomMigration, Long> {

    boolean existsByName(String name);
}
