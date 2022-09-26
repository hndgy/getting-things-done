package fr.hndgy.todos.repositories;

import fr.hndgy.todos.entities.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo,Long> {

    @Query("SELECT t " +
            "FROM Todo t JOIN AppUser u ON t.createdBy = u " +
            "WHERE u.email = :email " +
            "ORDER BY t.dateCreated")
    List<Todo> findAllByOwner(String email);

    @Query("SELECT t " +
            "FROM Todo t JOIN AppUser u ON t.createdBy = u " +
            "WHERE u.email = :email AND t.project is null AND t.isCompleted = false " +
            "ORDER BY t.dateCreated DESC")
    List<Todo> findByProjectNullAndOwner(String email);

    @Query("SELECT t " +
            "FROM Todo t JOIN AppUser u ON t.createdBy = u " +
            "WHERE u.email = :email AND t.project is not null AND t.isCompleted = false " +
            "ORDER BY t.dateCreated")
    List<Todo> findByProjectNotNullAndOwner(String email);

    @Query("SELECT t " +
            "FROM Todo t JOIN AppUser u ON t.createdBy = u " +
            "WHERE u.email = :email AND t.project is null " +
            "AND t.dueDate is not null " +
            "ORDER BY t.dateCreated")
    List<Todo> findByDueDateIsNotNullAndOwnerAndOwner(String email);

}
