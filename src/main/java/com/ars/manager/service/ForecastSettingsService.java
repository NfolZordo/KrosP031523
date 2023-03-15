package com.ars.manager.service;

import com.ars.manager.api.v1.mapper.ForecastSettingsMapper;
import com.ars.manager.domain.Forecast;
import com.ars.manager.domain.ForecastName;
import com.ars.manager.domain.document.CompanyIndicator;
import com.ars.manager.domain.document.Document;
import com.ars.manager.exception.NotFoundException;
import com.ars.manager.exception.NotImplementedException;
import com.ars.manager.repository.DocumentRepository;
import com.ars.manager.repository.ForecastRepository;
import lombok.AllArgsConstructor;
import org.openapitools.model.IndicatorRespDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ForecastSettingsService {

    private final ForecastSettingsMapper forecastSettingsMapper;
    private final DocumentRepository documentRepository;
    private final ForecastRepository forecastRepository;

    public List<IndicatorRespDto> setupSettings(String forecastId, String companyId)
            throws NotImplementedException, NotFoundException {
        Forecast forecast = forecastRepository.findByUuid(forecastId)
                .orElseThrow(() -> new NotFoundException("Not found Forecast by id: %s ".formatted(forecastId)));

        ForecastName forecastName = forecast.getValue();
        return switch (forecastName) {
            case RISK_OF_ENTERPRISE_BANKRUPTCY -> getSettingsTree(companyId, forecastName);
            case TEST_FORECAST_NAME -> throw new NotImplementedException("Not implemented yet");
        };
    }

    private List<IndicatorRespDto> getSettingsTree(String companyId, ForecastName forecastName) {
        List<Document> documents = documentRepository.findAllByCompanyUuid(companyId).stream()
                .filter(document -> getForecastNames(document).contains(forecastName))
                .toList();

        List<CompanyIndicator> indicators = documents.stream()
                .map(Document::getIndicators)
                .flatMap(List::stream)
                .toList();


        // TODO: 05.02.2023 add deep comparator for comparing company indicators tree

        return forecastSettingsMapper.toDtos(indicators);
    }


    private List<ForecastName> getForecastNames(Document document) {
        return document.getForecasts().stream()
                .map(Forecast::getValue)
                .toList();
    }
}
