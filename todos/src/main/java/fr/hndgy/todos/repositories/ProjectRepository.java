package fr.hndgy.todos.repositories;

import fr.hndgy.todos.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Query("select p from Project p join AppUser u on p.createdBy.id = u.id " +
            "where u.email = :email")
    List<Project> findAllByUser(String email);
}
