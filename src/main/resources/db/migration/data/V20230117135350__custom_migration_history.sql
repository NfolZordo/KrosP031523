CREATE TABLE IF NOT EXISTS custom_migration_history
(
    id           SERIAL       PRIMARY KEY,
    name         VARCHAR(100) NOT NULL UNIQUE,
    created_date TIMESTAMP    NOT NULL,
    edited_date  TIMESTAMP    NOT NULL
)
