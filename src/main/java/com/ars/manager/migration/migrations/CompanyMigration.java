package com.ars.manager.migration.migrations;


import com.ars.manager.domain.Address;
import com.ars.manager.domain.Company;
import com.ars.manager.domain.CustomMigration;
import com.ars.manager.repository.CompanyRepository;
import com.ars.manager.repository.CustomMigrationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CompanyMigration implements Migration {
    private static final String NAME = "54cd0ab4-a495-11ed-a8fc-0242ac120002";
    private static final String COMPANY_NAME_1 = "GlooGloo Gloo";
    private static final String COMPANY_NAME_2 = "COMPANY NAME";
    private static final String COUNTRY = "Ukraine";
    private static final String CITY = "Cherkasy";
    private static final String REGION = "Cherkasy region";
    private static final String STREET = "Khreshchatyk";
    private static final String BUILDING = "221";
    private static final String APARTMENT = "44";


    private final CompanyRepository companyRepository;

    private final CustomMigrationRepository customMigrationRepository;


    @Override
    public void applyMigration() {
        if (customMigrationRepository.existsByName(NAME)) {
            return;
        }

        Company companyGloo = getCompany(COMPANY_NAME_1);
        companyGloo.setMainAddress(mainAddress());
        companyRepository.save(companyGloo);

        Company companyAbstract = getCompany(COMPANY_NAME_2);
        companyAbstract.setMainAddress(mainAddress());
        companyRepository.save(companyAbstract);

        customMigrationRepository.save(createCustomMigration());
    }

    private Company getCompany(String companyName) {
        Company company = new Company();
        company.setName(companyName);
        return company;
    }

    private Address mainAddress() {
        Address address = new Address();

        address.setCountry(COUNTRY);
        address.setCity(CITY);
        address.setRegion(REGION);
        address.setStreet(STREET);
        address.setBuilding(BUILDING);
        address.setApartment(APARTMENT);

        return address;
    }

    private CustomMigration createCustomMigration() {
        CustomMigration customMigration = new CustomMigration();

        customMigration.setName(NAME);

        return customMigration;
    }
}
