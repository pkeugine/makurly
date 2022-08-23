package com.makurly.core.ui.dto;

import com.makurly.core.domain.Interaction;

public class InteractionResponse {

    private final Long id;

    private InteractionResponse(Long id) {
        this.id = id;
    }

    public static InteractionResponse of(Interaction interaction) {
        return new InteractionResponse(interaction.getId());
    }

    public Long getId() {
        return id;
    }
}
