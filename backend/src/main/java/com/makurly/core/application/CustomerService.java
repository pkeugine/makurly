package com.makurly.core.application;

import com.makurly.core.domain.Customer;
import com.makurly.core.domain.CustomerRepository;
import com.makurly.core.exception.UserAlreadyExistException;
import com.makurly.core.exception.UserNotExistException;
import com.makurly.core.ui.dto.CustomerRequest;
import com.makurly.core.ui.dto.CustomerResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerResponse create(CustomerRequest customerRequest) {
        Customer customer = customerRequest.toEntity();
        if (alreadyExists(customer)) {
            throw new UserAlreadyExistException();
        }
        customerRepository.save(customer);
        return CustomerResponse.of(customer);
    }

    private boolean alreadyExists(Customer customer) {
        return customerRepository.existsByName(customer.getName());
    }

    public CustomerResponse signIn(String customerName) {
        Customer existedCustomer = findByName(customerName);
        return CustomerResponse.of(existedCustomer);
    }

    private Customer findByName(String name) {
        return customerRepository.findByName(name)
            .orElseThrow(UserNotExistException::new);
    }

    @Transactional(readOnly = true)
    public CustomerResponse findById(Long id) {
        Customer customer = findCustomerById(id);
        return CustomerResponse.of(customer);
    }

    private Customer findCustomerById(Long id) {
        return customerRepository.findById(id)
            .orElseThrow(UserNotExistException::new);
    }
}
