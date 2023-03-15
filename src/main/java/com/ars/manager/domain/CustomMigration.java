package com.ars.manager.domain;


import com.ars.manager.domain.base.HistoryEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "custom_migration_history")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomMigration extends HistoryEntity {

    @Column(nullable = false, unique = true)
    String name;
}
