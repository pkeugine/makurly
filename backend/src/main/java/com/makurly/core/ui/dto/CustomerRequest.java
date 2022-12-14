package com.makurly.core.ui.dto;

import com.makurly.core.domain.Customer;
import com.makurly.core.domain.Gender;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class CustomerRequest {

    private String name;
    private Gender gender;
    private LocalDate birthDate;
    private String device;
    private String mainAddress;
    private String detailedAddress;

    protected CustomerRequest() {
    }

    public Customer toEntity() {
        return new Customer(name, gender, birthDate, device, mainAddress, detailedAddress, LocalDateTime.now());
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
