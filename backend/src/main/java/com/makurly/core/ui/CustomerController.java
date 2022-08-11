package com.makurly.core.ui;

import com.makurly.core.application.CustomerService;
import com.makurly.core.application.dto.CustomerRequest;
import com.makurly.core.application.dto.CustomerResponse;
import java.net.URI;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
    public ResponseEntity<CustomerResponse> createUser(@RequestBody CustomerRequest customerRequest) {
        CustomerResponse customerResponse = customerService.create(customerRequest);
        URI uri = URI.create(String.format("/customers/%d", customerResponse.getId()));
        return ResponseEntity.created(uri).body(customerResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> findUserById(@PathVariable Long id) {
        CustomerResponse customerResponse = customerService.findById(id);
        return ResponseEntity.ok(customerResponse);
    }

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerResponse> findUserByName(@RequestBody CustomerRequest customerRequest) {
        CustomerResponse customerResponse = customerService.findByName(customerRequest);
        return ResponseEntity.ok(customerResponse);
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> findAllUsers() {
        List<CustomerResponse> customerResponses = customerService.findAll();
        return ResponseEntity.ok(customerResponses);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CustomerResponse> updateUser(@PathVariable Long id, @RequestBody CustomerRequest customerRequest) {
        CustomerResponse customerResponse = customerService.updateById(id, customerRequest);
        return ResponseEntity.ok(customerResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        customerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
