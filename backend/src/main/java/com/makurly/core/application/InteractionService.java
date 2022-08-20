package com.makurly.core.application;

import com.makurly.core.domain.*;
import com.makurly.core.exception.UserNotExistException;
import com.makurly.core.ui.dto.InteractionRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

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

    public void makeInteraction(InteractionRequest interactionRequest){
        Customer customer = customerRepository
                .findById(interactionRequest.getCustomerId())
                .orElseThrow(UserNotExistException::new);
        Interaction interaction = new Interaction(customer, LocalDateTime.now());
        interactionRepository.save(interaction);
        interactionRequest.getInteractionItems().forEach(interactionItemRequest -> {
            Item item = itemRepository.findById(interactionItemRequest
                    .getItemId())
                    .orElseThrow(NoSuchElementException::new);
            InteractionItem interactionItem = new InteractionItem(interactionItemRequest.getQuantity(),interaction,item);
            interactionItemRepository.save(interactionItem);
        });
    }
}
