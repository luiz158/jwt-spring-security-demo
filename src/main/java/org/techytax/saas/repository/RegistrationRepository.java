package org.techytax.saas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.techytax.saas.domain.Registration;

import java.util.Collection;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {

    Collection<Registration> findByUser(String username);
}
