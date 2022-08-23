package com.makurly.core.ui.dto;

import com.makurly.core.domain.InteractionItem;

public class UserInteractionItemResponse {

    private Long id;

    private Integer quantity;

    private ItemResponse item;

    public UserInteractionItemResponse(Long id, Integer quantity, ItemResponse item) {
        this.id = id;
        this.quantity = quantity;
        this.item = item;
    }

    public Long getId() {
        return id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public ItemResponse getItem() {
        return item;
    }

    public static UserInteractionItemResponse of(InteractionItem userInteraction){
        return new UserInteractionItemResponse(userInteraction.getId()
            ,userInteraction.getQuantity()
            ,ItemResponse.of(userInteraction.getItem()));
    }
}
