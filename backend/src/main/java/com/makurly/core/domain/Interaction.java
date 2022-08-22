package com.makurly.core.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Interaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private LocalDateTime orderDate;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "interaction", orphanRemoval = true)
    private List<Recommend> recommends = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "interaction", orphanRemoval = true)
    private List<InteractionItem> interactionItems = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public List<Recommend> getRecommends() {
        return recommends;
    }

    public List<InteractionItem> getInteractionItems() {
        return interactionItems;
    }

    public Interaction() {
    }

    public Interaction(Customer customer, LocalDateTime orderDate) {
        this.customer = customer;
        this.orderDate = orderDate;
    }
}
