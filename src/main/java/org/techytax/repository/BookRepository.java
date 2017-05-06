package org.techytax.repository;

import org.springframework.data.repository.CrudRepository;
import org.techytax.domain.BalanceType;
import org.techytax.domain.BookValue;

import java.util.Collection;

public interface BookRepository extends CrudRepository<BookValue, Long> {

    Collection<BookValue> findByUser(String username);

    BookValue findBookValueByUserAndBalanceTypeAndBookYear(String username, BalanceType balanceType, int year);

    void deleteBookValues(String username);
}
