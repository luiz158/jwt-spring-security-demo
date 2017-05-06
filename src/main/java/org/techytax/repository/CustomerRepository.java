package org.techytax.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.techytax.domain.Customer;

import java.util.Collection;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    Collection<Customer> findByUser(String username);

    @Modifying
    @Query("delete from Customer c where c.user = ?1")
    void deleteCustomersByUser(String username);
}
