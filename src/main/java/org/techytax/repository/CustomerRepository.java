package org.techytax.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.techytax.domain.Customer;

import java.util.Collection;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Collection<Customer> findByUser(String username);
}
