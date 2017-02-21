package org.techytax.repository;

import org.springframework.data.repository.CrudRepository;
import org.techytax.domain.Cost;

import java.util.Collection;

public interface CostRepository extends CrudRepository<Cost, Long> {

    Collection<Cost> findByUser(String username);
}
