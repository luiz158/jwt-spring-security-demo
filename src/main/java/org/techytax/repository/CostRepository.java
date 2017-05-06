package org.techytax.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.techytax.domain.Cost;
import org.techytax.domain.CostType;

import java.time.LocalDate;
import java.util.Collection;

public interface CostRepository extends CrudRepository<Cost, Long> {

    Collection<Cost> findByUser(String username);

    @Query("select c from Cost c " +
      "where c.user = ?1 and c.costType = ?2 and c.date between ?3 and ?4 and c.amount is not null and c.amount > 0")
    Collection<Cost> findCosts(String username, CostType costType, LocalDate fromDate, LocalDate toDate);

    @Query("delete from Cost c where c.user = ?1")
    void deleteCostsByUser(String username);
}
