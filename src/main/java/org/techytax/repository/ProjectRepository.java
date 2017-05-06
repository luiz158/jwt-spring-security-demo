package org.techytax.repository;

import org.springframework.data.repository.CrudRepository;
import org.techytax.domain.Project;

import java.util.Collection;

public interface ProjectRepository extends CrudRepository<Project, Long> {

    Collection<Project> findByUser(String username);

    void deleteProjectsByUser(String username);
}
