package com.makurly.core.domain;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

    List<Cart> findCartsByCustomer(Customer customer);
}
