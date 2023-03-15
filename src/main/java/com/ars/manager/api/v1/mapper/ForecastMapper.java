package com.ars.manager.api.v1.mapper;

import com.ars.manager.domain.Forecast;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.openapitools.model.ForecastRespDto;

import java.util.List;


@Mapper(componentModel = "spring")
public interface ForecastMapper {

    List<ForecastRespDto> toDtos(List<Forecast> entities);

    @Mapping(target = "name", source = "value")
    ForecastRespDto toDto(Forecast entity);
}
