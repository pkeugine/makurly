package com.makurly.core.application;

import com.makurly.core.domain.Customer;
import com.makurly.core.domain.CustomerRepository;
import com.makurly.core.domain.Interaction;
import com.makurly.core.domain.InteractionItem;
import com.makurly.core.domain.InteractionItemRepository;
import com.makurly.core.domain.InteractionRepository;
import com.makurly.core.domain.Item;
import com.makurly.core.domain.ItemRepository;
import com.makurly.core.exception.UserNotExistException;
import com.makurly.core.ui.dto.InteractionRequest;
import com.makurly.core.ui.dto.InteractionResponse;
import com.makurly.core.ui.dto.UserInteractionResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class InteractionService {

    private final CustomerRepository customerRepository;

    private final ItemRepository itemRepository;

    private final InteractionRepository interactionRepository;

    private final InteractionItemRepository interactionItemRepository;

    public InteractionService(CustomerRepository customerRepository,
                              ItemRepository itemRepository,
                              InteractionRepository interactionRepository,
                              InteractionItemRepository interactionItemRepository) {
        this.customerRepository = customerRepository;
        this.itemRepository = itemRepository;
        this.interactionRepository = interactionRepository;
        this.interactionItemRepository = interactionItemRepository;
    }

    public InteractionResponse makeInteraction(InteractionRequest interactionRequest) {
        Customer customer = customerRepository
            .findById(interactionRequest.getCustomerId())
            .orElseThrow(UserNotExistException::new);
        Interaction interaction = new Interaction(customer, LocalDateTime.now());
        interactionRepository.save(interaction);
        interactionRequest.getInteractionItems().forEach(interactionItemRequest -> {
            Item item = itemRepository.findById(interactionItemRequest
                    .getItemId())
                .orElseThrow();
            InteractionItem interactionItem = new InteractionItem(interactionItemRequest.getQuantity(), interaction,
                item);
            interactionItemRepository.save(interactionItem);
        });
        return InteractionResponse.of(interaction);
    }
    public List<UserInteractionResponse> getUserInteractions(Long id){
        Customer customer = customerRepository.findById(id).orElseThrow();
        List<Interaction> interactions = interactionRepository.findAllByCustomer(customer);
        List<UserInteractionResponse> userInteractionResponses = new ArrayList();
        interactions.forEach(interaction -> {
            userInteractionResponses.add(UserInteractionResponse.of(interaction));
        });
        return userInteractionResponses;
    }
}
