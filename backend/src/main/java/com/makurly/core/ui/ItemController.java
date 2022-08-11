package com.makurly.core.ui;

import com.makurly.core.application.ItemService;
import com.makurly.core.application.dto.ItemRequest;
import com.makurly.core.application.dto.ItemResponse;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public ResponseEntity<ItemResponse> createItem(@RequestBody ItemRequest itemRequest) {
        ItemResponse itemResponse = itemService.create(itemRequest);
        URI uri = URI.create(String.format("/items/%d", itemResponse.getId()));
        return ResponseEntity.created(uri).body(itemResponse);
    }

    @GetMapping
    public ResponseEntity<List<ItemResponse>> findAllItems() {
        List<ItemResponse> itemResponses = itemService.findAll();
        return ResponseEntity.ok(itemResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemResponse> findItem(@PathVariable Long id) {
        ItemResponse itemResponse = itemService.findById(id);
        return ResponseEntity.ok(itemResponse);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ItemResponse> updateItem(@PathVariable Long id, @RequestBody ItemRequest itemRequest) {
        ItemResponse itemResponse = itemService.updateById(id, itemRequest);
        return ResponseEntity.ok(itemResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        itemService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
