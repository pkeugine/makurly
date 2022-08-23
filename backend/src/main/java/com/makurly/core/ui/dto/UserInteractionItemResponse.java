package com.makurly.core.ui.dto;

import com.makurly.core.domain.InteractionItem;
import java.util.List;
import java.util.stream.Collectors;

public class UserInteractionItemResponse {

    private final Long id;
    private final Integer quantity;
    private final ItemResponse item;

    private UserInteractionItemResponse(Long id, Integer quantity, ItemResponse item) {
        this.id = id;
        this.quantity = quantity;
        this.item = item;
    }

    public static UserInteractionItemResponse of(InteractionItem userInteraction) {
        return new UserInteractionItemResponse(
            userInteraction.getId(),
            userInteraction.getQuantity(),
            ItemResponse.of(userInteraction.getItem())
        );
    }

    public static List<UserInteractionItemResponse> ofList(List<InteractionItem> interactionItems) {
        return interactionItems.stream()
            .map(UserInteractionItemResponse::of)
            .collect(Collectors.toList());
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
}
