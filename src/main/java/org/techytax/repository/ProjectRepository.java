package org.techytax.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.techytax.domain.Project;

import java.util.Collection;

public interface ProjectRepository extends CrudRepository<Project, Long> {

    Collection<Project> findByUser(String username);

    @Query("delete from Project p where p.user = ?1")
    void deleteProjectsByUser(String username);
}
