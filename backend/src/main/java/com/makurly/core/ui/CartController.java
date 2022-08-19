package com.makurly.core.ui;

import com.makurly.core.application.CartService;
import com.makurly.core.ui.dto.CartDeleteRequest;
import com.makurly.core.ui.dto.CartRequest;
import com.makurly.core.ui.dto.CartResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }

    @PostMapping("/delete")
    public ResponseEntity<Void> deleteCarts(@RequestBody CartDeleteRequest cartDeleteRequest){
        cartService.deleteCarts(cartDeleteRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long id){
        cartService.deleteCart(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartResponse> findCartById(@PathVariable Long id){
        CartResponse responseBody = cartService.findCartById(id);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }


}
