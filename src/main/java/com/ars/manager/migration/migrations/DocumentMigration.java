package com.ars.manager.migration.migrations;

import com.ars.manager.domain.Company;
import com.ars.manager.domain.CustomMigration;
import com.ars.manager.domain.document.CompanyIndicator;
import com.ars.manager.domain.document.Document;
import com.ars.manager.domain.document.DocumentType;
import com.ars.manager.domain.document.ValueType;
import com.ars.manager.repository.CompanyRepository;
import com.ars.manager.repository.CustomMigrationRepository;
import com.ars.manager.repository.ForecastRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@Service
@AllArgsConstructor
public class DocumentMigration implements Migration {
    private static final String NAME = "e6ca8672-a4bc-11ed-b9df-0242ac120003";
    private static final String COMPANY_NAME = "COMPANY NAME";
    private final CustomMigrationRepository customMigrationRepository;
    private final CompanyRepository companyRepository;
    private final ForecastRepository forecastRepository;

    @Override
    public void applyMigration() {
        if (customMigrationRepository.existsByName(NAME)) {
            return;
        }

        Company company = companyRepository.findCompanyByName(COMPANY_NAME);

        Document document = getDocument();

        CompanyIndicator industrialIndicator = getCompanyIndicator("Business processes", "Industrial");

        industrialIndicator.addChildIndicators(
                getCompanyIndicator(
                        "Calculation method",
                        "Growth (decrease) rate of industrial output (%)",
                        "44,88"));
        industrialIndicator.addChildIndicators(
                getCompanyIndicator(
                        "Calculation method",
                        "Rate of increase (decrease) cost of production (%)",
                        "8,53"));
        industrialIndicator.addChildIndicators(
                getCompanyIndicator(
                        "Calculation method",
                        "Specific weight of the cost of production in the total amount of expenses (%)",
                        "41,1"));
        document.addIndicator(industrialIndicator);


        CompanyIndicator administrativeIndicator = getCompanyIndicator("Business processes", "Administrative");

        administrativeIndicator.addChildIndicators(
                getCompanyIndicator(
                        "Calculation method",
                        "Income growth (decrease) rate (%)",
                        "46,94"));

        administrativeIndicator.addChildIndicators(
                getCompanyIndicator(
                        "Calculation method",
                        "Rate of increase (decrease) of costs (%)",
                        "2,91"));
        document.addIndicator(administrativeIndicator);

        forecastRepository.findAll()
                .forEach(document::addForecast);

        company.addDocument(document);

        customMigrationRepository.save(createCustomMigration());
    }

    private Document getDocument() {
        Document document = new Document();

        document.setType(DocumentType.REPORT);
        document.setData(OffsetDateTime.parse("2017-12-03T10:15:30+01:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME));

        return document;
    }

    private CompanyIndicator getCompanyIndicator(String type, String name) {
        CompanyIndicator companyIndicator = new CompanyIndicator();

        companyIndicator.setType(type);
        companyIndicator.setName(name);

        return companyIndicator;
    }


    private CompanyIndicator getCompanyIndicator(String type, String name, String value) {
        CompanyIndicator companyIndicator = new CompanyIndicator();

        companyIndicator.setType(type);
        companyIndicator.setName(name);
        companyIndicator.setValue(value);
        companyIndicator.setValueType(ValueType.DOUBLE);

        return companyIndicator;
    }


    private CustomMigration createCustomMigration() {
        CustomMigration customMigration = new CustomMigration();

        customMigration.setName(NAME);

        return customMigration;
    }
}
