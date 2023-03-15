package com.ars.manager.api.v1.mapper;

import com.ars.manager.domain.document.CompanyIndicator;
import org.mapstruct.Mapper;
import org.openapitools.model.IndicatorRespDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ForecastSettingsMapper {

    List<IndicatorRespDto> toDtos(List<CompanyIndicator> entities);

    IndicatorRespDto toDto(CompanyIndicator entity);
}
