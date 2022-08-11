package com.makurly.core.application.dto;

import com.makurly.core.domain.Item;

public class ItemResponse {

    private Long id;
    private String name;
    private String imageUrl;

    protected ItemResponse() {
    }

    private ItemResponse(Long id, String name, String imageUrl) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public static ItemResponse of(Item item) {
        return new ItemResponse(
            item.getId(),
            item.getName(),
            item.getImageUrl());
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
