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
import java.util.List;
import java.util.stream.Collectors;
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

    public InteractionResponse mapInteractionItem(InteractionRequest interactionRequest) {
        Interaction interaction = createInteraction(interactionRequest);
        createInteractionItems(interactionRequest, interaction);
        return InteractionResponse.of(interaction);
    }

    private Interaction createInteraction(InteractionRequest interactionRequest) {
        Customer customer = customerRepository.findById(interactionRequest.getCustomerId())
            .orElseThrow(UserNotExistException::new);
        Interaction interaction = new Interaction(customer, LocalDateTime.now());
        interactionRepository.save(interaction);
        return interaction;
    }

    private void createInteractionItems(InteractionRequest interactionRequest, Interaction interaction) {
        interactionRequest.getInteractionItems().forEach(interactionItemRequest -> {
            Item item = itemRepository.findById(interactionItemRequest.getItemId()).orElseThrow();
            InteractionItem interactionItem = new InteractionItem(
                interactionItemRequest.getQuantity(),
                interaction,
                item
            );
            interactionItemRepository.save(interactionItem);
        });
    }

    public List<UserInteractionResponse> getUserInteractions(Long id){
        Customer customer = customerRepository.findById(id).orElseThrow();
        List<Interaction> interactions = interactionRepository.findAllByCustomer(customer);
        return interactions.stream()
            .map(UserInteractionResponse::of)
            .collect(Collectors.toList());
    }
}
