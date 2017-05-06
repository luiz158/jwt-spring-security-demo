package org.techytax.repository;

import org.springframework.data.repository.CrudRepository;
import org.techytax.domain.CostType;

public interface CostTypeRepository extends CrudRepository<CostType, Long> {
}
