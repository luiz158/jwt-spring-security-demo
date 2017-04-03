package org.techytax.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.techytax.domain.Activum;
import org.techytax.domain.BalanceType;

import java.util.Collection;

public interface ActivumRepository extends CrudRepository<Activum, Long> {

    Collection<Activum> findByUser(String username);

//    @Query("select a from Activum a " +
//      "where c.user = ?1 and c.costType = ?2 and c.date between ?3 and ?4 and c.amount is not null and c.amount > 0")
//    Collection<Activum> findActivums(String username, BalanceType balanceType);
}
