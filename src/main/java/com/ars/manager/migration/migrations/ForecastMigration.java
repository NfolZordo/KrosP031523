package com.ars.manager.migration.migrations;

import com.ars.manager.domain.CustomMigration;
import com.ars.manager.domain.Forecast;
import com.ars.manager.domain.ForecastName;
import com.ars.manager.repository.CustomMigrationRepository;
import com.ars.manager.repository.ForecastRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;


@Service
@AllArgsConstructor
public class ForecastMigration implements Migration {

    private static final String NAME = "da23b2ac-a487-11ed-a8fc-0242ac120002";
    private final CustomMigrationRepository customMigrationRepository;
    private final ForecastRepository forecastRepository;

    @Override
    public void applyMigration() {
        if (customMigrationRepository.existsByName(NAME)) {
            return;
        }

        List<Forecast> forecasts = Arrays.stream(ForecastName.values())
                .map(this::createForecast)
                .toList();

        forecastRepository.saveAll(forecasts);
        customMigrationRepository.save(createCustomMigration());
    }

    private Forecast createForecast(ForecastName forecastName) {
        Forecast forecast = new Forecast();

        forecast.setValue(forecastName);

        return forecast;
    }

    private CustomMigration createCustomMigration() {
        CustomMigration customMigration = new CustomMigration();

        customMigration.setName(NAME);

        return customMigration;
    }
}
