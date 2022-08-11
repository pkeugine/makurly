package com.makurly.core.application.dto;

import com.makurly.core.domain.Customer;

public class CustomerResponse {

    private Long id;
    private String name;
    private int age;

    protected CustomerResponse() {
    }

    private CustomerResponse(Long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public static CustomerResponse of(Customer customer) {
        return new CustomerResponse(
            customer.getId(),
            customer.getName(),
            customer.getAge()
        );
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
