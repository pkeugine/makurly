package com.makurly.core.ui.dto;

import com.makurly.core.domain.Item;

public class ItemResponse {

    private Long id;
    private String name;
    private String imageUrl;
    private String category;

    protected ItemResponse() {
    }

    public ItemResponse(Long id, String name, String imageUrl, String category) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.category = category;
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

    public String getCategory() {
        return category;
    }

    public static ItemResponse of(Item item){
        return new ItemResponse(
                item.getId(),
                item.getName(),
                item.getImageUrl(),
                item.getCategory());
    }
}
