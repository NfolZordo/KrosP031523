package com.ars.manager.migration;

import com.ars.manager.migration.migrations.CompanyMigration;
import com.ars.manager.migration.migrations.DocumentMigration;
import com.ars.manager.migration.migrations.ForecastMigration;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MigrationService {
    private final ForecastMigration forecastDefaultValues;
    private final CompanyMigration companyMigration;
    private final DocumentMigration documentMigration;

    @EventListener(ApplicationReadyEvent.class)
    public void migration() {
        forecastDefaultValues.applyMigration();
        companyMigration.applyMigration();
        documentMigration.applyMigration();
    }
}
