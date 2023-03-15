package com.ars.manager.api.v1.controller;

import com.ars.manager.service.ForecastService;
import com.ars.manager.service.ForecastSettingsService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.openapitools.api.ForecastApi;
import org.openapitools.model.ForecastRespDto;
import org.openapitools.model.IndicatorRespDto;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class ForecastController implements ForecastApi {

    private final ForecastService forecastService;
    private final ForecastSettingsService forecastSettingsService;


    @Override
    public List<ForecastRespDto> getAllForecasts() {

        return forecastService.getAll();
    }

    @Override
    public List<ForecastRespDto> getForecasts(String companyId) {
        return forecastService.getByCompanyId(companyId);

    }

    @SneakyThrows
    @Override
    public List<IndicatorRespDto> getForecastsTree(String forecastId, String companyId) {
        return forecastSettingsService.setupSettings(forecastId, companyId);
    }
}
