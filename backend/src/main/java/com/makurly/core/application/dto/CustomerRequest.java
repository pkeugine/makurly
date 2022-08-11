package com.makurly.core.application.dto;

import com.makurly.core.domain.Customer;
import com.makurly.core.domain.Gender;

public class CustomerRequest {

    private String name;
    private int age;
    private Gender gender;

    protected CustomerRequest() {
    }

    private CustomerRequest(String name, int age, Gender gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public static CustomerRequest of(String name, int age, Gender gender) {
        return new CustomerRequest(name, age, gender);
    }

    public Customer toEntity() {
        return new Customer(name, age, gender);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
