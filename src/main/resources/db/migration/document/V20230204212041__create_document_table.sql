CREATE TABLE IF NOT EXISTS document
(
    id              SERIAL         PRIMARY KEY,
    uuid            VARCHAR(100)   NOT NULL    UNIQUE,
    type            VARCHAR(100)   NOT NULL,
    data            TIMESTAMP      NOT NULL,
    indicators      jsonb,
    company_id      BIGINT         NOT NULL,
    created_date    TIMESTAMP      NOT NULL,
    edited_date     TIMESTAMP      NOT NULL
);

CREATE TABLE IF NOT EXISTS document_forecast
(
    document_id               BIGINT        NOT NULL,
    forecast_id               BIGINT        NOT NULL
);

