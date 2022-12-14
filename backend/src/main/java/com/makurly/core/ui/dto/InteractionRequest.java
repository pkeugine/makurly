package com.makurly.core.ui.dto;

import java.util.List;

public class InteractionRequest {

    private Long customerId;
    private List<InteractionItemRequest> interactionItems;

    protected InteractionRequest() {
    }

    public Long getCustomerId() {
        return customerId;
    }

    public List<InteractionItemRequest> getInteractionItems() {
        return interactionItems;
    }
}
