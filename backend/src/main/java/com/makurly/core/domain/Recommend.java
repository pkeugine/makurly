package com.makurly.core.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Recommend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "interaction_id")
    private Interaction interaction;

    @ManyToOne(fetch = FetchType.LAZY)
    private Item item;

    private Integer discountRate;

    public Recommend(Customer customer, Interaction interaction, Item item, Integer discountRate) {
        this.customer = customer;
        this.interaction = interaction;
        this.item = item;
        this.discountRate = discountRate;
    }

    public Long getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Interaction getInteraction() {
        return interaction;
    }

    public Item getItem() {
        return item;
    }

    public Integer getDiscountRate() {
        return discountRate;
    }

    public Recommend() {
    }
}
