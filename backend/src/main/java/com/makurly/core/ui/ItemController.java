package com.makurly.core.ui;


import com.makurly.core.application.ItemService;
import com.makurly.core.ui.dto.ItemResponse;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public ResponseEntity<List<ItemResponse>> findAllItems() {
        List<ItemResponse> itemResponses = itemService.findAllItems();
        return ResponseEntity.ok(itemResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemResponse> findItemsById(@PathVariable Long id) {
        ItemResponse itemResponse = itemService.findItemById(id);
        return ResponseEntity.ok(itemResponse);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<ItemResponse>> findItemsByCategory(@RequestParam String category) {
        List<ItemResponse> itemResponses = itemService.findItemsByCategory(category);
        return ResponseEntity.ok(itemResponses);
    }
}
