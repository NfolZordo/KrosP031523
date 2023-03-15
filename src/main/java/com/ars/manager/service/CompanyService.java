package com.ars.manager.service;

import com.ars.manager.api.v1.mapper.CompanyMapper;
import com.ars.manager.client.CompanyAdapter;
import com.ars.manager.domain.Company;
import com.ars.manager.repository.CompanyRepository;
import lombok.AllArgsConstructor;
import org.openapitools.model.CompanyRespDto;
import org.openapitools.model.DeleteDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyAdapter companyAdapter;
    private final CompanyMapper companyMapper;

    public CompanyRespDto getById(String id) {
        Company company = companyRepository.findCompanyByUuid(id);
        return companyMapper.toDto(company);
    }


    public List<CompanyRespDto> getAll() {
        List<Company> companies = companyRepository.findAll();
        return companyMapper.toDtos(companies);
    }

    @Transactional
    public DeleteDto deleteById(String id) {
        companyRepository.deleteCompanyByUuid(id);

        companyAdapter.deleteById(id);

        return new DeleteDto().id(UUID.fromString(id));
    }
}
