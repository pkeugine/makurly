package com.makurly.core.ui;

import com.makurly.core.application.CustomerService;
import com.makurly.core.ui.dto.CustomerRequest;
import com.makurly.core.ui.dto.CustomerResponse;
import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody CustomerRequest customerRequest){
        CustomerResponse responseBody = customerService.createCustomer(customerRequest);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseBody);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<CustomerResponse> signIn(@RequestBody CustomerRequest customerRequest){
        CustomerResponse responseBody = customerService.signIn(customerRequest.getName());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseBody);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> findCustomerById(@PathVariable Long id){
        CustomerResponse responseBody = customerService.getCustomerById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseBody);
    }
}
