package com.makurly.core.ui;

import com.makurly.core.application.CustomerService;
import com.makurly.core.ui.dto.CustomerRequest;
import com.makurly.core.ui.dto.CustomerResponse;
import java.net.URI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody CustomerRequest customerRequest) {
        CustomerResponse customerResponse = customerService.createCustomer(customerRequest);
        URI uri = URI.create(String.format("/%d", customerResponse.getId()));
        return ResponseEntity.created(uri).body(customerResponse);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<CustomerResponse> signIn(@RequestBody CustomerRequest customerRequest) {
        CustomerResponse customerResponse = customerService.signIn(customerRequest.getName());
        return ResponseEntity.ok(customerResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> findCustomerById(@PathVariable Long id) {
        CustomerResponse customerResponse = customerService.getCustomerById(id);
        return ResponseEntity.ok(customerResponse);
    }
}
