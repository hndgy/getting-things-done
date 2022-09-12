package fr.hndgy.todos.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "todos")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private LocalDateTime dateCreated;
    private LocalDateTime dueDate;
    private boolean isCompleted;
    private boolean isPriority;

    @ManyToOne
    private Project project;

    @ManyToOne
    private AppUser createdBy;

}
