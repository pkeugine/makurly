package com.makurly.core.domain;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecommendRepository extends JpaRepository<Recommend, Long> {
    Optional<List<Recommend>> findAllByInteraction(Interaction interaction);
}
