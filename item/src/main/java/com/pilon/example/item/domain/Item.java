package com.pilon.example.item.domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@EnableJpaAuditing
@Entity
@Table(name = "items")
@EntityListeners(AuditingEntityListener.class)
public class Item {

    private static DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private long id;

    private String description;

    @OneToMany(mappedBy = "item")
    Set<ItemImage> images;

    @CreatedBy
    private String createdBy;
    
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }
    
    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getCreated() {
        return this.created;
    }

    @Override
    public String toString() {
        return String.format("Item[id=%d, createdBy='%s', created='%d', description='%s']", id, createdBy, DATE_FORMAT.format(created), description);
    }
}