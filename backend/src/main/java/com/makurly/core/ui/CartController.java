package com.makurly.core.ui;

import com.makurly.core.application.CartService;
import com.makurly.core.ui.dto.CartDeleteRequest;
import com.makurly.core.ui.dto.CartRequest;
import com.makurly.core.ui.dto.CartResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public ResponseEntity<CartResponse> addCart(@RequestBody CartRequest cartRequest){
        CartResponse responseBody = cartService.addCart(cartRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseBody);
    }

    @PostMapping("/delete")
    public ResponseEntity<Void> deleteCarts(@RequestBody CartDeleteRequest cartDeleteRequest){
        cartService.deleteCarts(cartDeleteRequest);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long id){
        cartService.deleteCart(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartResponse> findCartById(@PathVariable(name="id") Long cartId){
        CartResponse responseBody = cartService.findCartById(cartId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseBody);
    }

    @GetMapping()
    public ResponseEntity<List<CartResponse>> findCartsByCustomerId(@RequestParam(name="id")Long customerId){
        List<CartResponse> responseBody = cartService.findCartsByCustomerId(customerId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseBody);
    }


}
