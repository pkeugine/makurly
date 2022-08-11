package com.makurly.core.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String imageUrl;

    protected Item() {
    }

    public Item(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public void updateWith(Item other) {
        this.name = other.name;
        this.imageUrl = other.imageUrl;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
