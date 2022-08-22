package com.makurly.core.application;

import com.makurly.core.application.dto.RecommendRequest;
import com.makurly.core.application.dto.RecommendResponse;
import com.makurly.core.domain.Interaction;
import com.makurly.core.domain.InteractionItem;
import com.makurly.core.domain.InteractionRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Transactional
public class WebClientService {

    private final InteractionRepository interactionRepository;
    private final WebClient webClient;

    public WebClientService(InteractionRepository interactionRepository, WebClient webClient) {
        this.interactionRepository = interactionRepository;
        this.webClient = webClient;
    }

    public RecommendResponse getItemsFromInteractionEx(Long id) {
        Interaction interaction = interactionRepository.findById(id).orElseThrow();
        Long customerId = interaction.getCustomer().getId();
        List<InteractionItem> interactions = interaction.getInteractionItems();
        List<Long> itemIds = new ArrayList<>();
        interactions.forEach(interactionItem -> {
            for (int i = 0; i < interactionItem.getQuantity(); i++) {
                itemIds.add(interactionItem.getItem().getId());
            }
        });
        RecommendRequest body = new RecommendRequest(customerId, itemIds, id);
        RecommendResponse recommendResponse = webClient
            .post()
            .uri("/ex-recommend")
            .bodyValue(body)
            .retrieve()
            .bodyToMono(RecommendResponse.class)
            .block();

        return recommendResponse;
    }
    public RecommendResponse getItemsFromInteractionIm(Long id) {
        Interaction interaction = interactionRepository.findById(id).orElseThrow();
        Long customerId = interaction.getCustomer().getId();
        List<InteractionItem> interactions = interaction.getInteractionItems();
        List<Long> itemIds = new ArrayList<>();
        interactions.forEach(interactionItem -> {
            for (int i = 0; i < interactionItem.getQuantity(); i++) {
                itemIds.add(interactionItem.getItem().getId());
            }
        });
        RecommendRequest body = new RecommendRequest(customerId, itemIds, id);
        RecommendResponse recommendResponse = webClient
            .post()
            .uri("/ex-recommend")
            .bodyValue(body)
            .retrieve()
            .bodyToMono(RecommendResponse.class)
            .block();

        return recommendResponse;
    }
}
