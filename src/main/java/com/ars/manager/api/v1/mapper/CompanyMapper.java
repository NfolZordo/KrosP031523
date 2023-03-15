package com.ars.manager.api.v1.mapper;


import com.ars.manager.domain.Company;
import org.mapstruct.Mapper;
import org.openapitools.model.CompanyRespDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompanyMapper {


    List<CompanyRespDto> toDtos(List<Company> entities);

    CompanyRespDto toDto(Company entity);
}
