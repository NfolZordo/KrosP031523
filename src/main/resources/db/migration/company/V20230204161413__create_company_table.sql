CREATE TABLE IF NOT EXISTS company
(
    id                      SERIAL        UNIQUE PRIMARY KEY,
    uuid                    VARCHAR(100)  NOT NULL  UNIQUE,
    name                    VARCHAR(100)  NOT NULL,
    address_id              BIGINT        NOT NULL,
    created_date            TIMESTAMP     NOT NULL,
    edited_date             TIMESTAMP     NOT NULL
);
