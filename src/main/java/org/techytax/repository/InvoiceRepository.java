package org.techytax.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.techytax.domain.Invoice;

import java.time.LocalDate;
import java.util.Collection;

public interface InvoiceRepository extends CrudRepository<Invoice, Long> {

    Collection<Invoice> findByUser(String username);

    @Query("select i from Invoice i " +
      "where i.user = ?1 and i.sent between ?2 and ?3")
    Collection<Invoice> findInvoices(String username, LocalDate fromDate, LocalDate toDate);
}
