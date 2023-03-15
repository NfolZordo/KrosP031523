package com.ars.manager.domain;


import com.ars.manager.domain.base.HistoryEntity;
import com.ars.manager.domain.document.Document;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Forecast extends HistoryEntity {


    @Setter(AccessLevel.NONE)
    private String uuid;

    @Enumerated(EnumType.STRING)
    private ForecastName value;
    private String description;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "document_forecast",
            joinColumns = @JoinColumn(name = "document_id"),
            inverseJoinColumns = @JoinColumn(name = "forecast_id"))
    private Set<Document> documents = new HashSet<>();


    @PrePersist
    public void onCreate() {
        uuid = UUID.randomUUID().toString();
    }

}
