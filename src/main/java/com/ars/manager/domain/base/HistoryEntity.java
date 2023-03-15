package com.ars.manager.domain.base;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;


@Getter
@Setter
@MappedSuperclass
@EntityListeners({AuditingListener.class})
public abstract class HistoryEntity extends BaseEntity {

    @Column(nullable = false)
    private OffsetDateTime createdDate;

    @Column(nullable = false)
    private OffsetDateTime editedDate;
}
