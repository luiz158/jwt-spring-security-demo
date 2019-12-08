package org.techytax.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.techytax.domain.Project;

import java.util.Collection;

public interface ProjectRepository extends CrudRepository<Project, Long> {

    Collection<Project> findByUser(String username);

    @Query("select p from Project p where p.user = ?1 and p.endDate is null")
    Collection<Project> findCurrentProjects(String username);

    @Modifying
    @Query("delete from Project p where p.user = ?1")
    void deleteProjectsByUser(String username);
}
