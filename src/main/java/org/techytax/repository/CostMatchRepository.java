package org.techytax.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.techytax.domain.CostMatch;

import java.util.Collection;

public interface CostMatchRepository extends CrudRepository<CostMatch, Long> {

    Collection<CostMatch> findByUser(String username);

    @Query("delete from CostMatch c where c.user = ?1")
    void deleteCostMatchesByUser(String username);
}
