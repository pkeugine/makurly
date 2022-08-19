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

    private LocalDate birthDate;

    private String device;

    private String mainAddress;

    private String detailedAddress;

    private LocalDateTime signInDate;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "customer", orphanRemoval = true)
    private List<Cart> carts = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "customer", orphanRemoval = true)
    private List<Interaction> interactions = new ArrayList<>();


    protected Customer() {
    }

    public Customer(String name, Gender gender, LocalDate birthDate, String device, String mainAddress,
                    String detailedAddress, LocalDateTime signInDate) {
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
        this.device = device;
        this.mainAddress = mainAddress;
        this.detailedAddress = detailedAddress;
        this.signInDate = signInDate;
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
