package com.makurly.core.application.dto;

import com.makurly.core.domain.Customer;

public class CustomerRequest {

    private String name;
    private int age;

    protected CustomerRequest() {
    }

    private CustomerRequest(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public static CustomerRequest of(String name, int age) {
        return new CustomerRequest(name, age);
    }

    public Customer toEntity() {
        return new Customer(name, age);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
