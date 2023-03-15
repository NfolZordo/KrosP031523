package com.ars.manager.domain;

import com.ars.manager.domain.base.HistoryEntity;
import com.ars.manager.domain.document.Document;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.util.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Company extends HistoryEntity {

    @Setter(AccessLevel.NONE)
    private String uuid;
    private String name;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address mainAddress;

    @LazyCollection(LazyCollectionOption.EXTRA)
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "company")
    private List<Document> documents = new ArrayList<>();


    public void addDocument(Document document) {
        documents.add(document);
        document.setCompany(this);
    }

    @PrePersist
    public void onCreate() {
        uuid = UUID.randomUUID().toString();
    }
}
