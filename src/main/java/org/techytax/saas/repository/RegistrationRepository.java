package org.techytax.saas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.techytax.saas.domain.Registration;

import java.util.Collection;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {

    Collection<Registration> findByUser(String username);

    @Modifying
    @Query("delete from Registration r where r.user = ?1")
    void deleteRegistrationByUser(String username);
}
