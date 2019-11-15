package com.pilon.example.item.domain;

// import javax.persistence.Column;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="items")
public class Item {

    @Id
    // TODO: Need to fix auto-generation of ID
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Column(columnDefinition = "serial")    
    private long id;

    private String description;

    protected Item() {

    }

    public Item(long id, String description) {
        this.id = id;
        this.description = description;
    }

    @JsonProperty("id")
    public long getId() {
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