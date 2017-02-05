package org.techytax.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.techytax.domain.Project;

import java.util.Collection;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    Collection<Project> findByUser(String username);
}
