package com.makurly.core.application.dto;

import com.makurly.core.domain.Customer;
import com.makurly.core.domain.Gender;

public class CustomerResponse {

    private Long id;
    private String name;
    private int age;
    private Gender gender;

    protected CustomerResponse() {
    }

    private CustomerResponse(Long id, String name, int age, Gender gender) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public static CustomerResponse of(Customer customer) {
        return new CustomerResponse(
            customer.getId(),
            customer.getName(),
            customer.getAge(),
            customer.getGender()
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

    public Gender getGender() {
        return gender;
    }
}
