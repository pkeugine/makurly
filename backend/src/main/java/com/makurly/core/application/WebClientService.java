package com.makurly.core.application;

import com.makurly.core.application.dto.RecommendRequest;
import com.makurly.core.application.dto.RecommendResponse;
import com.makurly.core.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class WebClientService {

    private final InteractionRepository interactionRepository;
    private final WebClient webClient;

    public WebClientService(InteractionRepository interactionRepository, WebClient webClient) {
        this.interactionRepository = interactionRepository;
        this.webClient = webClient;
    }

    public RecommendResponse getItemsFromInteraction(Long id){
        Interaction interaction = interactionRepository.findById(id).orElseThrow();
        Long customerId = interaction.getCustomer().getId();
        List<InteractionItem> interactions = interaction.getInteractionItems();
        List<Long> itemIds=new ArrayList<>();
        interactions.forEach(interactionItem -> {
            for (int i =0 ; i < interactionItem.getQuantity();i++){
                itemIds.add(interactionItem.getItem().getId());
            }
        });
        RecommendRequest body = new RecommendRequest(customerId,itemIds,id);
        RecommendResponse recommendResponse = webClient
                .post()
                .uri("/recommend")
                .bodyValue(body)
                .retrieve()
                .bodyToMono(RecommendResponse.class)
                .block();
        return recommendResponse;
    }
}
