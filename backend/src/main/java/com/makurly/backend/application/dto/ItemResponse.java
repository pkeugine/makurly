package com.makurly.backend.application.dto;

import com.makurly.backend.domain.Item;

public class ItemResponse {

    private Long id;
    private String name;

    protected ItemResponse() {}

    private ItemResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static ItemResponse of(Item item) {
        return new ItemResponse(
            item.getId(),
            item.getName()
        );
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
