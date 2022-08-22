package com.makurly.core.ui.dto;

import com.makurly.core.domain.Cart;

public class CartResponse {

    private Long id;

    private ItemResponse item;

    private Integer quantity;

    public Long getId() {
        return id;
    }

    public ItemResponse getItem() {
        return item;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public CartResponse(Long id, ItemResponse item, Integer quantity) {
        this.id = id;
        this.item = item;
        this.quantity = quantity;
    }

    public static CartResponse of(Cart cart) {
        return new CartResponse(cart.getId(), ItemResponse.of(cart.getItem()), cart.getQuantity());
    }
}
