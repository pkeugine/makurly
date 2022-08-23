package com.makurly.core.ui.dto;

import com.makurly.core.domain.Cart;

public class CartResponse {

    private final Long id;
    private final ItemResponse item;
    private final Integer quantity;

    public CartResponse(Long id, ItemResponse item, Integer quantity) {
        this.id = id;
        this.item = item;
        this.quantity = quantity;
    }

    public static CartResponse of(Cart cart) {
        return new CartResponse(cart.getId(), ItemResponse.of(cart.getItem()), cart.getQuantity());
    }

    public Long getId() {
        return id;
    }

    public ItemResponse getItem() {
        return item;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
