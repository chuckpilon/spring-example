package com.pilon.example.item.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")    
    private long id;

    private String description;

    @OneToMany(mappedBy="item")
    Set<ItemImage> images;

    public Item() {

    }

    @JsonProperty("id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("images")
    public Set<ItemImage> getImages() {
        return this.images;
    }

    public void setImages(Set<ItemImage> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return String.format("Item[id=%d, description='%s']", id, description);
    }
}