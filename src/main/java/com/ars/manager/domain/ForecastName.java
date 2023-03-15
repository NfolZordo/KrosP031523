package com.ars.manager.domain;

import lombok.Getter;

@Getter
public enum ForecastName {

    RISK_OF_ENTERPRISE_BANKRUPTCY("Risk of enterprise bankruptcy"),
    TEST_FORECAST_NAME("Test forecast name");

    private final String name;

    ForecastName(String name) {
        this.name = name;
    }
}
