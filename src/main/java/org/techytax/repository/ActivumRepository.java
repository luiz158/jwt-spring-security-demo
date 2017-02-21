package org.techytax.repository;

import org.springframework.data.repository.CrudRepository;
import org.techytax.domain.Activum;

import java.util.Collection;

public interface ActivumRepository extends CrudRepository<Activum, Long> {

    Collection<Activum> findByUser(String username);
}
