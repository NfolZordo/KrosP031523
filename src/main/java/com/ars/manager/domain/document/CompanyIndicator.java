package com.ars.manager.domain.document;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.util.Objects.isNull;

@Data
public class CompanyIndicator implements Serializable {
    @Serial
    private static final long serialVersionUID = 12L;

    @Setter(AccessLevel.NONE)
    private String uuid = UUID.randomUUID().toString();
    private String type; // 1. Бізнес-процеси           || 2. Методика розрахунку
    private String name; // 1. Виробничий               || 2. Темп приросту (зниження)промислового обсягувиробництва (%)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String value; //                            || 2. 44,88
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ValueType valueType;  //                    || 2. DOUBLE
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<CompanyIndicator> indicators; //       || 2. Бізнес-процеси uuid

    public void addChildIndicators(CompanyIndicator companyIndicator) {
        if (isNull(indicators)) {
            indicators = new ArrayList<>();
        }
        indicators.add(companyIndicator);
    }
}
