package com.makurly.core.application;

import com.makurly.core.application.dto.ItemRequest;
import com.makurly.core.application.dto.ItemResponse;
import com.makurly.core.domain.Item;
import com.makurly.core.domain.ItemRepository;
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

    public ItemResponse create(ItemRequest itemRequest) {
        Item item = itemRequest.toEntity();
        itemRepository.save(item);
        return ItemResponse.of(item);
    }

    @Transactional(readOnly = true)
    public ItemResponse findById(Long id) {
        return ItemResponse.of(findItem(id));
    }

    private Item findItem(Long id) {
        return itemRepository.findById(id)
            .orElseThrow(IllegalArgumentException::new);
    }

    @Transactional(readOnly = true)
    public List<ItemResponse> findAll() {
        List<Item> items = itemRepository.findAll();
        return items.stream()
            .map(ItemResponse::of)
            .collect(Collectors.toList());
    }

    public ItemResponse updateById(Long id, ItemRequest itemRequest) {
        Item existingItem = findItem(id);
        Item updatedItem = itemRequest.toEntity();
        existingItem.updateWith(updatedItem);
        return ItemResponse.of(existingItem);
    }

    public void deleteById(Long id) {
        itemRepository.deleteById(id);
    }
}
