package com.ars.manager.repository;

import com.ars.manager.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    Company findCompanyByName(String name);

    Company findCompanyByUuid(String uuid);

    void deleteCompanyByUuid(String uuid);
}
