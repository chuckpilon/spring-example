package com.pilon.example.item.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")    
    private Long id;

    private String description;

    protected Item() {

    }

    public Item(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return String.format("Item[id=%d, description='%s']", id, description);
    }
}