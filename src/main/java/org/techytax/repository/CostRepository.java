package org.techytax.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.techytax.domain.Cost;

import java.util.Collection;

public interface CostRepository extends JpaRepository<Cost, Long> {

    Collection<Cost> findByUser(String username);
}
