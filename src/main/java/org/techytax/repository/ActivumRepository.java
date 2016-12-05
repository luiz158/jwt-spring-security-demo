package org.techytax.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.techytax.domain.Activum;

import java.util.Collection;

public interface ActivumRepository extends JpaRepository<Activum, Long> {

    Collection<Activum> findByUser(String username);
}
