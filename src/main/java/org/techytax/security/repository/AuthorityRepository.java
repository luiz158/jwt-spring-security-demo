package org.techytax.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.techytax.model.security.Authority;
import org.techytax.model.security.AuthorityName;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Authority findByName(AuthorityName authorityName);
}
