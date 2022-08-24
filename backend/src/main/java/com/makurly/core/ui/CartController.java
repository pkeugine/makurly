package com.makurly.core.ui;

import com.makurly.core.application.CartService;
import com.makurly.core.ui.dto.CartDeleteRequest;
import com.makurly.core.ui.dto.CartRequest;
import com.makurly.core.ui.dto.CartResponse;
import java.net.URI;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carts")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public ResponseEntity<CartResponse> createCart(@RequestBody CartRequest cartRequest) {
        CartResponse cartResponse = cartService.addCart(cartRequest);
        URI uri = URI.create(String.format("/%d", cartResponse.getId()));
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .location(uri)
            .body(cartResponse);
    }

    @GetMapping
    public ResponseEntity<List<CartResponse>> findCartsByCustomerId(@RequestParam(name = "id") Long customerId) {
        List<CartResponse> cartResponses = cartService.findCartsByCustomerId(customerId);
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(cartResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartResponse> findCartById(@PathVariable Long id) {
        CartResponse cartResponse = cartService.findCartById(id);
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(cartResponse);
    }

    @PostMapping("/delete")
    public ResponseEntity<Void> deleteCarts(@RequestBody CartDeleteRequest cartDeleteRequest) {
        cartService.deleteCarts(cartDeleteRequest);
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long id) {
        cartService.deleteCart(id);
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build();
    }
}
