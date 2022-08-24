package com.makurly.core.application;

import com.makurly.core.application.dto.RecommendResponse;
import com.makurly.core.domain.Customer;
import com.makurly.core.domain.CustomerRepository;
import com.makurly.core.domain.Interaction;
import com.makurly.core.domain.InteractionRepository;
import com.makurly.core.domain.Item;
import com.makurly.core.domain.ItemRepository;
import com.makurly.core.domain.Recommend;
import com.makurly.core.domain.RecommendRepository;
import com.makurly.core.ui.dto.PersonalRecommendResponse;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class RecommendService {

    private final ItemRepository itemRepository;
    private final CustomerRepository customerRepository;
    private final RecommendRepository recommendRepository;
    private final InteractionRepository interactionRepository;

    public RecommendService(ItemRepository itemRepository,
                            CustomerRepository customerRepository,
                            RecommendRepository recommendRepository,
                            InteractionRepository interactionRepository) {
        this.itemRepository = itemRepository;
        this.customerRepository = customerRepository;
        this.recommendRepository = recommendRepository;
        this.interactionRepository = interactionRepository;
    }

    public List<PersonalRecommendResponse> findItemsByIds(RecommendResponse recommendResponse) {
        Customer customer = customerRepository.findById(recommendResponse.getCustomerId()).orElseThrow();
        Interaction interaction = interactionRepository.findById(recommendResponse.getInteractionId()).orElseThrow();
        List<Long> itemIds = recommendResponse.getItemIds();
        List<Item> items = itemRepository.findAllById(itemIds);
        int[] rate = {10, 15, 20, 25, 30};
        return createPersonalRecommendResponse(customer, interaction, items, rate);
    }

    private List<PersonalRecommendResponse> createPersonalRecommendResponse(Customer customer,
                                                                            Interaction interaction,
                                                                            List<Item> items,
                                                                            int[] rate) {
        List<Recommend> recommends = items.stream()
            .map(item -> new Recommend(customer, interaction, item, rate[(int) (Math.random() * 4)]))
            .collect(Collectors.toList());
        recommendRepository.saveAll(recommends);
        return recommends.stream()
            .map(PersonalRecommendResponse::of)
            .collect(Collectors.toList());
    }
}
