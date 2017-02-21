package org.techytax.repository;

import org.springframework.data.repository.CrudRepository;
import org.techytax.domain.Invoice;

import java.util.Collection;

public interface InvoiceRepository extends CrudRepository<Invoice, Long> {

    Collection<Invoice> findByUser(String username);
}
