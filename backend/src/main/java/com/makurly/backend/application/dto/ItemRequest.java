package com.makurly.backend.application.dto;

import com.makurly.backend.domain.Item;

public class ItemRequest {

    private String name;

    protected ItemRequest() {}

    private ItemRequest(String name) {
        this.name = name;
    }

    public static ItemRequest of(String name) {
        return new ItemRequest(name);
    }

    public Item toEntity() {
        return new Item(name);
    }

    public String getName() {
        return name;
    }
}
