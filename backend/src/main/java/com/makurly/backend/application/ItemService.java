package com.makurly.backend.application;

import com.makurly.backend.application.dto.ItemRequest;
import com.makurly.backend.application.dto.ItemResponse;
import com.makurly.backend.domain.Item;
import com.makurly.backend.domain.ItemRepository;
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
