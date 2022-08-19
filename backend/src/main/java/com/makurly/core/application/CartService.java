package com.makurly.core.application;

import com.makurly.core.domain.*;
import com.makurly.core.ui.dto.CartDeleteRequest;
import com.makurly.core.ui.dto.CartRequest;
import com.makurly.core.ui.dto.CartResponse;
import java.util.ArrayList;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CartService {

    private final CartRepository cartRepository;
    private final CustomerRepository customerRepository;
    private final ItemRepository itemRepository;

    public CartService(CartRepository cartRepository, CustomerRepository customerRepository,
                       ItemRepository itemRepository) {
        this.cartRepository = cartRepository;
        this.customerRepository = customerRepository;
        this.itemRepository = itemRepository;
    }

    public CartResponse addCart(CartRequest cartRequest){
        Customer customer = customerRepository.findById(cartRequest.getCustomerId()).orElseThrow();
        Item item = itemRepository.findById(cartRequest.getItemId()).orElseThrow();
        Cart cart = cartRequest.toEntity(customer,item);
        cartRepository.save(cart);
        return CartResponse.of(cart);
    }

    public CartResponse findCartById(Long id){
        Cart cart = cartRepository.findById(id).orElseThrow();
        return CartResponse.of(cart);
    }

    public List<CartResponse> findCartsByCustomerId(Long customerId){
        Customer customer = customerRepository.findById(customerId).orElseThrow();
        List<Cart> carts= cartRepository.findCartsByCustomer(customer);
        List<CartResponse> cartResponses = new ArrayList<>();
        carts.forEach(cart->{
            cartResponses.add(CartResponse.of(cart));
        });
        return cartResponses;
    }

    public void deleteCart(Long id){
        cartRepository.deleteById(id);
    }

    public void deleteCarts(CartDeleteRequest cartDeleteRequest){
        cartDeleteRequest.getCartIds().forEach(id->{
            cartRepository.deleteById(id);
        });
    }

}
