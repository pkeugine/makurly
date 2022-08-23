package com.makurly.core.ui.dto;

import com.makurly.core.domain.Customer;
import com.makurly.core.domain.Gender;
import java.time.LocalDate;

public class CustomerResponse {

    private final Long id;
    private final String name;
    private final Gender gender;
    private final LocalDate birthDate;
    private final String device;
    private final String mainAddress;
    private final String detailedAddress;

    private CustomerResponse(Long id,
                             String name,
                             Gender gender,
                             LocalDate birthDate,
                             String device,
                             String mainAddress,
                             String detailedAddress) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
        this.device = device;
        this.mainAddress = mainAddress;
        this.detailedAddress = detailedAddress;
    }

    public static CustomerResponse of(Customer customer) {
        return new CustomerResponse(
            customer.getId(),
            customer.getName(),
            customer.getGender(),
            customer.getBirthDate(),
            customer.getDevice(),
            customer.getMainAddress(),
            customer.getDetailedAddress()
        );
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
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
}
