package org.techytax.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.techytax.domain.Invoice;

import java.util.Collection;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    Collection<Invoice> findByUser(String username);
}
