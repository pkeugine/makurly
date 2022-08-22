package com.makurly.core.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;


public class RecommendRequest {

    @JsonProperty("user_idx")
    private Long customerId;

    @JsonProperty("item_idx")
    private List<Long> itemIds;

    @JsonProperty("interaction_idx")
    private Long interactionId;

    public RecommendRequest(Long customerId, List<Long> itemIds, Long interactionId) {
        this.customerId = customerId;
        this.itemIds = itemIds;
        this.interactionId = interactionId;
    }
}
