package com.makurly.core.domain;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InteractionRepository extends JpaRepository<Interaction, Long> {
    List<Interaction> findAllByCustomer(Customer customer);
}
