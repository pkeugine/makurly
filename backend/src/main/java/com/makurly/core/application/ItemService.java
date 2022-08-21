package com.makurly.core.application;


import com.makurly.core.domain.Item;
import com.makurly.core.domain.ItemRepository;
import com.makurly.core.ui.dto.ItemResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<ItemResponse> getAllItems(){
        List<Item> items = itemRepository.findAll();
        List<ItemResponse> itemResponses = new ArrayList<>();
        items.forEach(item -> {
            itemResponses.add(ItemResponse.of(item));
        });
        return itemResponses;
    }

    public List<ItemResponse> findItemsByCategory(String category){
        List<Item> items = itemRepository.findAllByCategory(category);
        List<ItemResponse> itemResponses = new ArrayList<>();
        items.forEach(item -> {
            itemResponses.add(ItemResponse.of(item));
        });
        return itemResponses;
    }

    public ItemResponse findItemById(Long id){
        Item item = itemRepository.findById(id).orElseThrow();
        ItemResponse itemResponse = ItemResponse.of(item);
        return itemResponse;
    }
}
