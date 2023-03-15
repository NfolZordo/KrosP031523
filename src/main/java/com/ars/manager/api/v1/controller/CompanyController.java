package com.ars.manager.api.v1.controller;

import com.ars.manager.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.CompanyApi;
import org.openapitools.model.CompanyRespDto;
import org.openapitools.model.DeleteDto;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class CompanyController implements CompanyApi {

    private final CompanyService companyService;

    @Override
    public CompanyRespDto getCompanyById(String id) {

        return companyService.getById(id);
    }

    @Override
    public List<CompanyRespDto> getAllCompanies() {

        return companyService.getAll();
    }

    @Override
    public DeleteDto deleteCompanyById(String id) {

        return companyService.deleteById(id);
    }
}
