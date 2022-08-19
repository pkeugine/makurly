package com.makurly.core.ui.dto;

import com.makurly.core.domain.Item;

public class ItemRequest {

    private String name;
    private String imageUrl;

    protected ItemRequest() {
    }

    private ItemRequest(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public static ItemRequest of(String name, String imageUrl) {
        return new ItemRequest(name, imageUrl);
    }

    public Item toEntity() {
        return new Item(name, imageUrl);
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
