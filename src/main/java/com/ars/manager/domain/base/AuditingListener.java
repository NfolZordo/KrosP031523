package com.ars.manager.domain.base;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.OffsetDateTime;

public class AuditingListener {

    @PrePersist
    public void onCreate(HistoryEntity entity) {
        OffsetDateTime date = OffsetDateTime.now();

        entity.setCreatedDate(date);
        entity.setEditedDate(date);
    }

    @PreUpdate
    public void onUpdate(HistoryEntity entity) {
        OffsetDateTime date = OffsetDateTime.now();

        entity.setEditedDate(date);
    }
}
