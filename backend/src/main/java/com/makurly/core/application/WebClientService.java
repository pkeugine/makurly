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

    public RecommendResponse receiveItemsFromInteractionEx(Long id) {
        Interaction interaction = findInteractionById(id);
        Long customerId = findCustomerId(interaction);
        List<InteractionItem> interactionItems = interaction.getInteractionItems();
        List<Long> itemIds = findInteractionItemIds(interactionItems);
        RecommendRequest recommendRequest = new RecommendRequest(customerId, itemIds, id);
        return requestRecommendation(recommendRequest);
    }

    private Interaction findInteractionById(Long id) {
        return interactionRepository.findById(id)
            .orElseThrow();
    }

    private Long findCustomerId(Interaction interaction) {
        return interaction
            .getCustomer()
            .getId();
    }

    private List<Long> findInteractionItemIds(List<InteractionItem> interactionItems) {
        List<Long> itemIds = new ArrayList<>();
        for (InteractionItem interactionItem : interactionItems) {
            addItemIds(itemIds, interactionItem);
        }
        return itemIds;
    }

    private void addItemIds(List<Long> itemIds, InteractionItem interactionItem) {
        Long itemId = interactionItem.getItem().getId();
        for (int i = 0; i < interactionItem.getQuantity(); i++) {
            itemIds.add(itemId);
        }
    }

    private RecommendResponse requestRecommendation(RecommendRequest body) {
        return webClient
            .post()
            .uri("/ex-recommend")
            .bodyValue(body)
            .retrieve()
            .bodyToMono(RecommendResponse.class)
            .block();
    }
}
