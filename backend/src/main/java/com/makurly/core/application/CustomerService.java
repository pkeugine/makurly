package com.makurly.core.application;

import com.makurly.core.application.dto.CustomerRequest;
import com.makurly.core.application.dto.CustomerResponse;
import com.makurly.core.domain.Customer;
import com.makurly.core.domain.CustomerRepository;
import java.util.List;
import java.util.stream.Collectors;
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
        customerRepository.save(customer);
        return CustomerResponse.of(customer);
    }

    @Transactional(readOnly = true)
    public CustomerResponse findById(Long id) {
        return CustomerResponse.of(findUser(id));
    }

    private Customer findUser(Long id) {
        return customerRepository.findById(id)
            .orElseThrow(IllegalArgumentException::new);
    }

    @Transactional(readOnly = true)
    public List<CustomerResponse> findAll() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
            .map(CustomerResponse::of)
            .collect(Collectors.toList());
    }

    public CustomerResponse updateById(Long id, CustomerRequest customerRequest) {
        Customer existingCustomer = findUser(id);
        Customer updatedCustomer = customerRequest.toEntity();
        existingCustomer.updateWith(updatedCustomer);
        return CustomerResponse.of(existingCustomer);
    }

    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }
}
