CREATE TABLE IF NOT EXISTS address
(
    id                      SERIAL        UNIQUE PRIMARY KEY,
    uuid                    VARCHAR(100)  NOT NULL  UNIQUE,
    country                 VARCHAR(100)  NOT NULL,
    region                  VARCHAR(100),
    city                    VARCHAR(100),
    village                 VARCHAR(100),
    zip                     VARCHAR(100),
    street                  VARCHAR(100),
    building                VARCHAR(100),
    apartment               VARCHAR(100),
    created_date            TIMESTAMP     NOT NULL,
    edited_date             TIMESTAMP     NOT NULL
);
