package org.techytax.repository;

import org.springframework.data.repository.CrudRepository;
import org.techytax.domain.CostType;

import java.util.Collection;

public interface CostTypeRepository extends CrudRepository<CostType, Long> {

//    Collection<CostType> findByUser(String username);
}
