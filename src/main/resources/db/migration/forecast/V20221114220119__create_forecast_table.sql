CREATE TABLE IF NOT EXISTS forecast
(
    id                      SERIAL        UNIQUE PRIMARY KEY,
    uuid                    VARCHAR(100)  NOT NULL  UNIQUE,
    value                   VARCHAR(100)  NOT NULL,
    description             VARCHAR(100),
    created_date            TIMESTAMP     NOT NULL,
    edited_date             TIMESTAMP     NOT NULL
);
