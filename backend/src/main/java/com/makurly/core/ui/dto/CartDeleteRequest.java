package com.makurly.core.ui.dto;


import java.util.List;

public class CartDeleteRequest {

    private List<Long> cartIds;

    public List<Long> getCartIds() {
        return cartIds;
    }
}
