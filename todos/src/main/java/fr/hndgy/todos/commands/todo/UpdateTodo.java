package fr.hndgy.todos.commands.todo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public final class UpdateTodo {
    private String title;
    private LocalDateTime dueDate;
    private Long project;
    private boolean isPriority;
    private boolean isNext;
}
