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

    public CustomerResponse createCustomer(CustomerRequest customerRequest){
        Customer newCustomer=customerRequest.toEntity();
        if(customerRepository.existsByName(customerRequest.getName())){
            throw new UserAlreadyExistException();
        }
        customerRepository.save(newCustomer);
        return CustomerResponse.of(newCustomer);
    }

    public CustomerResponse signIn(String customerName){
        Customer existedCustomer = customerRepository.
                findByName(customerName)
                .orElseThrow(UserNotExistException::new);
        return CustomerResponse.of(existedCustomer);
    }

    public CustomerResponse getCustomerById(Long id){
        Customer customer = customerRepository
                .findById(id)
                .orElseThrow(UserNotExistException::new);
        return CustomerResponse.of(customer);
    }
}
