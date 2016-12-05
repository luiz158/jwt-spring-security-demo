package org.techytax.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.techytax.domain.BookValue;

import java.util.Collection;

public interface BookRepository extends JpaRepository<BookValue, Long> {

    Collection<BookValue> findByUser(String username);
}
