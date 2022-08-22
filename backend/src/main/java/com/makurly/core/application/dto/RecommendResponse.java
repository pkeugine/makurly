package com.makurly.core.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class RecommendResponse {

    @JsonProperty("items")
    private List<Long> itemIds;

    @JsonProperty("user_id")
    private Long customerId;

    @JsonProperty("interaction_id")
    private Long interactionId;

    public List<Long> getItemIds() {
        return itemIds;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public Long getInteractionId() {
        return interactionId;
    }
}
