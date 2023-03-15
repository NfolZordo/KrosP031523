package com.ars.manager.migration.migrations;

import org.springframework.transaction.annotation.Transactional;

interface Migration {

    @Transactional
    void applyMigration();
}
