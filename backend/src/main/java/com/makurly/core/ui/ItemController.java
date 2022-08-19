package com.makurly.core.ui;


import com.makurly.core.application.ItemService;
import com.makurly.core.ui.dto.ItemResponse;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public ResponseEntity<List<ItemResponse>> getAllItems(){
        List<ItemResponse> responseBody = itemService.getAllItems();

        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<ItemResponse>> getItemsByCategory(@RequestParam String category){
        List<ItemResponse> responseBody = itemService.findItemsByCategory(category);

        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemResponse> getItemById(@PathVariable Long id){
        ItemResponse responseBody = itemService.findItemById(id);

        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
