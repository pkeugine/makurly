package com.makurly.core.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Transient
    private int age;
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "customer", orphanRemoval = true)
    private List<Cart> carts = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "customer", orphanRemoval = true)
    private List<Interaction> interactions = new ArrayList<>();
    private LocalDate birthDate;

    private String device;

    private String mainAddress;

    private String detailedAddress;

    private LocalDateTime signInDate;


    protected Customer() {
    }

    public Customer(String name, int age, Gender gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public void updateWith(Customer other) {
        this.name = other.name;
        this.age = other.age;
        this.gender = other.gender;
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getDevice() {
        return device;
    }

    public String getMainAddress() {
        return mainAddress;
    }

    public String getDetailedAddress() {
        return detailedAddress;
    }

    public LocalDateTime getSignInDate() {
        return signInDate;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public List<Interaction> getInteractions() {
        return interactions;
    }
}
