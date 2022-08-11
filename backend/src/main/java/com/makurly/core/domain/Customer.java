package com.makurly.core.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;

    protected Customer() {
    }

    public Customer(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void updateWith(Customer other) {
        this.name = other.name;
        this.age = other.age;
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
