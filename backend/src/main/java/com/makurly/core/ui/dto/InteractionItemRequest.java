package com.makurly.core.ui.dto;

public class InteractionItemRequest {

    private Long itemId;
    private Integer quantity;

    protected InteractionItemRequest() {
    }

    public Long getItemId() {
        return itemId;
    }

    public Integer getQuantity() {
        return quantity;
    }


}
