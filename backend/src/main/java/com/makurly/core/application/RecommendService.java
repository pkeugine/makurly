package com.makurly.core.application;

import com.makurly.core.application.dto.RecommendResponse;
import com.makurly.core.domain.*;
import com.makurly.core.ui.dto.ItemResponse;
import com.makurly.core.ui.dto.PersonalRecommendResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class RecommendService {

    private final ItemRepository itemRepository;

    private final CustomerRepository customerRepository;

    private final RecommendRepository recommendRepository;

    private final InteractionRepository interactionRepository;


    public RecommendService(ItemRepository itemRepository, CustomerRepository customerRepository, RecommendRepository recommendRepository, InteractionRepository interactionRepository) {
        this.itemRepository = itemRepository;
        this.customerRepository = customerRepository;
        this.recommendRepository = recommendRepository;
        this.interactionRepository = interactionRepository;
    }

    public List<PersonalRecommendResponse> findItemsByIds(RecommendResponse recommendResponse){
        Customer customer = customerRepository.findById(recommendResponse.getCustomerId()).orElseThrow();
        Interaction interaction = interactionRepository.findById(recommendResponse.getInteractionId()).orElseThrow();
        List<Long> itemIds = recommendResponse.getItemIds();
        List<PersonalRecommendResponse> personalRecommendResponses = new ArrayList<>();
        itemIds.forEach(id->{
            Item item = itemRepository.findById(id).orElseThrow();
            Recommend recommend = new Recommend(customer,interaction,item,10);
            recommendRepository.save(recommend);
            personalRecommendResponses.add(PersonalRecommendResponse.of(recommend));
        });
        return personalRecommendResponses;
    }
}
