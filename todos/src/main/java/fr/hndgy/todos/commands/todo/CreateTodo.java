package fr.hndgy.todos.commands.todo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public final class CreateTodo {
    @NotNull
    private String title;
    private LocalDateTime dueDate;
    private boolean priority;
}
