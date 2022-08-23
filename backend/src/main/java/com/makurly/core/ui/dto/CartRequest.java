package com.makurly.core.ui.dto;

import com.makurly.core.domain.Cart;
import com.makurly.core.domain.Customer;
import com.makurly.core.domain.Item;

public class CartRequest {

    private Long customerId;
    private Long itemId;
    private Integer quantity;

    public Long getCustomerId() {
        return customerId;
    }

    public Long getItemId() {
        return itemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Cart toEntity(Customer customer, Item item) {
        return new Cart(customer, item, quantity);
    }
}
