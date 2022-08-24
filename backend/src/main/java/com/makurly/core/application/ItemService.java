package com.makurly.core.application;


import com.makurly.core.domain.Item;
import com.makurly.core.domain.ItemRepository;
import com.makurly.core.ui.dto.ItemResponse;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Transactional(readOnly = true)
    public List<ItemResponse> findAllItems() {
        List<Item> items = itemRepository.findAll();
        return createItemResponses(items);
    }

    private List<ItemResponse> createItemResponses(List<Item> items) {
        return items.stream()
            .map(ItemResponse::of)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ItemResponse findItemById(Long id) {
        Item item = itemRepository.findById(id).orElseThrow();
        return ItemResponse.of(item);
    }

    @Transactional(readOnly = true)
    public List<ItemResponse> findItemsByCategory(String category) {
        List<Item> items = itemRepository.findAllByCategory(category);
        return createItemResponses(items);
    }
}
