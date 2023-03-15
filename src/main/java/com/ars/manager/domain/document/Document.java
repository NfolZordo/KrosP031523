package com.ars.manager.domain.document;

import com.ars.manager.domain.Company;
import com.ars.manager.domain.Forecast;
import com.ars.manager.domain.base.HistoryEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.OffsetDateTime;
import java.util.*;

import static java.util.Objects.isNull;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Document extends HistoryEntity {

    @Setter(AccessLevel.NONE)
    private String uuid;
    private DocumentType type;
    private OffsetDateTime data;
    @JdbcTypeCode(SqlTypes.JSON)
    private List<CompanyIndicator> indicators;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @ManyToMany(mappedBy = "documents")
    private Set<Forecast> forecasts = new HashSet<>();

    @PrePersist
    public void onCreate() {
        uuid = UUID.randomUUID().toString();
    }

    public void addIndicator(CompanyIndicator companyIndicator) {
        if (isNull(indicators)) {
            indicators = new ArrayList<>();
        }
        indicators.add(companyIndicator);
    }

    public void addForecast(Forecast forecast) {
        forecasts.add(forecast);
        forecast.getDocuments().add(this);
    }
}
