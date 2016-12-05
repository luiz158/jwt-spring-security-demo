package org.techytax.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.techytax.domain.CostMatch;

import java.util.Collection;

public interface CostMatchRepository extends JpaRepository<CostMatch, Long> {

    Collection<CostMatch> findByUser(String username);
}
