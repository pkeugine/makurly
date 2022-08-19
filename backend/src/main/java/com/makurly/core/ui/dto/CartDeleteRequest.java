package com.makurly.core.ui.dto;


import com.makurly.core.domain.Cart;
import com.makurly.core.domain.Customer;
import com.makurly.core.domain.Item;

import java.util.List;

public class CartDeleteRequest {

    private List<Long> cartIds;

    public List<Long> getCartIds() {
        return cartIds;
    }
}
