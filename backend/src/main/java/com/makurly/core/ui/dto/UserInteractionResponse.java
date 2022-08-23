package com.makurly.core.ui.dto;

import com.makurly.core.domain.Interaction;
import com.makurly.core.domain.InteractionItem;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserInteractionResponse {

    private Long id;

    private LocalDateTime orderDate;

    private List<UserInteractionItemResponse> interactions;

    public UserInteractionResponse(Long id, LocalDateTime orderDate, List<UserInteractionItemResponse> interactions) {
        this.id = id;
        this.orderDate = orderDate;
        this.interactions = interactions;
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

    public static UserInteractionResponse of(Interaction interaction){
        List<InteractionItem> interactionItems = interaction.getInteractionItems();
        List<UserInteractionItemResponse> userInteractionItemResponses = new ArrayList();
        interactionItems.forEach(interactionItem -> {
            userInteractionItemResponses.add(UserInteractionItemResponse.of(interactionItem));
        });
        return new UserInteractionResponse(interaction.getId(),interaction.getOrderDate(),userInteractionItemResponses);
    }
}
