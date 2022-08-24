package com.makurly.core.application;

import com.makurly.core.domain.Cart;
import com.makurly.core.domain.CartRepository;
import com.makurly.core.domain.Customer;
import com.makurly.core.domain.CustomerRepository;
import com.makurly.core.domain.Item;
import com.makurly.core.domain.ItemRepository;
import com.makurly.core.ui.dto.CartDeleteRequest;
import com.makurly.core.ui.dto.CartRequest;
import com.makurly.core.ui.dto.CartResponse;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CartService {

    private final CustomerRepository customerRepository;
    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;

    public CartService(CustomerRepository customerRepository,
                       ItemRepository itemRepository,
                       CartRepository cartRepository) {
        this.customerRepository = customerRepository;
        this.itemRepository = itemRepository;
        this.cartRepository = cartRepository;
    }

    public CartResponse addCart(CartRequest cartRequest) {
        Customer customer = findCustomer(cartRequest);
        Item item = findItem(cartRequest);
        Cart cart = cartRequest.toEntity(customer, item);
        cartRepository.save(cart);
        return CartResponse.of(cart);
    }

    private Customer findCustomer(CartRequest cartRequest) {
        return customerRepository.findById(cartRequest.getItemId())
            .orElseThrow();
    }

    private Item findItem(CartRequest cartRequest) {
        return itemRepository.findById(cartRequest.getItemId())
            .orElseThrow();
    }

    @Transactional(readOnly = true)
    public CartResponse findCartById(Long id) {
        Cart cart = cartRepository.findById(id).orElseThrow();
        return CartResponse.of(cart);
    }

    @Transactional(readOnly = true)
    public List<CartResponse> findCartsByCustomerId(Long customerId) {
        Customer customer = findCustomerById(customerId);
        List<Cart> carts = cartRepository.findCartsByCustomer(customer);
        return carts.stream()
            .map(CartResponse::of)
            .collect(Collectors.toList());
    }

    private Customer findCustomerById(Long customerId) {
        return customerRepository.findById(customerId)
            .orElseThrow();
    }

    public void deleteCart(Long id) {
        cartRepository.deleteById(id);
    }

    public void deleteCarts(CartDeleteRequest cartDeleteRequest) {
        List<Long> cartIds = cartDeleteRequest.getCartIds();
        cartRepository.deleteAllById(cartIds);
    }
}
