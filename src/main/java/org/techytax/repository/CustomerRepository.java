package org.techytax.repository;

import org.springframework.data.repository.CrudRepository;
import org.techytax.domain.Customer;

import java.util.Collection;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    Collection<Customer> findByUser(String username);

    void deleteCustomersByUser(String username);
}
