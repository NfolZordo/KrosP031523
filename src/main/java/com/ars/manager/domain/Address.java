package com.ars.manager.domain;

import com.ars.manager.domain.base.HistoryEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity(name = "address")
@Getter
@Setter
@NoArgsConstructor
public class Address extends HistoryEntity {

    @Setter(AccessLevel.NONE)
    private String uuid;
    private String country;
    private String region;
    private String city;
    private String village;
    private String zip;
    private String street;
    private String building;
    private String apartment;

    @OneToOne(mappedBy = "mainAddress")
    private Company company;

    @PrePersist
    public void onCreate() {
        uuid = UUID.randomUUID().toString();
    }
}
