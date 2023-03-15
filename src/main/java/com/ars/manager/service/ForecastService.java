package com.ars.manager.service;


import com.ars.manager.api.v1.mapper.ForecastMapper;
import com.ars.manager.domain.Forecast;
import com.ars.manager.repository.ForecastRepository;
import lombok.AllArgsConstructor;
import org.openapitools.model.ForecastRespDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ForecastService {

    private final ForecastRepository forecastRepository;
    private final ForecastMapper forecastMapper;


    public List<ForecastRespDto> getAll() {
        List<Forecast> forecasts = forecastRepository.findAll();
        return forecastMapper.toDtos(forecasts);
    }

    public List<ForecastRespDto> getByCompanyId(String companyId) {
        List<Forecast> forecasts = forecastRepository.findByAllByCompanyId(companyId);
        return forecastMapper.toDtos(forecasts);
    }
}
