package com.makurly.core.ui.dto;

import com.makurly.core.domain.Interaction;
import com.makurly.core.domain.InteractionItem;
import java.time.LocalDateTime;
import java.util.List;

public class UserInteractionResponse {

    private final Long id;
    private final LocalDateTime orderDate;
    private final List<UserInteractionItemResponse> interactions;

    public UserInteractionResponse(Long id, LocalDateTime orderDate, List<UserInteractionItemResponse> interactions) {
        this.id = id;
        this.orderDate = orderDate;
        this.interactions = interactions;
    }

    public static UserInteractionResponse of(Interaction interaction) {
        List<InteractionItem> interactionItems = interaction.getInteractionItems();
        return new UserInteractionResponse(
            interaction.getId(),
            interaction.getOrderDate(),
            UserInteractionItemResponse.ofList(interactionItems)
        );
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public List<UserInteractionItemResponse> getInteractions() {
        return interactions;
    }
}
