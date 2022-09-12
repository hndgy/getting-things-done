package fr.hndgy.todos.controllers;

import fr.hndgy.todos.commands.todo.CreateTodo;
import fr.hndgy.todos.commands.todo.UpdateTodo;
import fr.hndgy.todos.commands.todo.UpdateTodoProject;
import fr.hndgy.todos.entities.Todo;
import fr.hndgy.todos.exceptions.ProjectNotFoundException;
import fr.hndgy.todos.exceptions.TodoNotFoundException;
import fr.hndgy.todos.exceptions.UserNotFoundException;
import fr.hndgy.todos.services.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/todo")
@CrossOrigin("*")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/inbox")
    public ResponseEntity<List<Todo>> getInbox(Principal principal){
        return ResponseEntity.ok(todoService.getListTodosInboxByUser(principal.getName()));
    }

    @GetMapping("/planned")
    public ResponseEntity<List<Todo>> getPlanned(Principal principal){
        return ResponseEntity.ok(todoService.getListTodosPlannedByUser(principal.getName()));
    }  

    @GetMapping("/asap")
    public ResponseEntity<List<Todo>> getAsap(Principal principal){
        return ResponseEntity.ok(todoService.getListTodosASAPByUser(principal.getName()));
    }
    @GetMapping("/")
    public ResponseEntity<List<Todo>> getAllTodos(Principal principal){
        return ResponseEntity.ok(todoService.getListTodosByUser(principal.getName()));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodo(@PathVariable Long id) throws TodoNotFoundException {
        return ResponseEntity.ok(todoService.getTodo(id));
    }
    @PostMapping("/")
    public ResponseEntity<Todo> createTodo(@RequestBody CreateTodo createTodo, HttpServletRequest request, Principal principal) throws UserNotFoundException {
        Todo newTodo = todoService.createTodo(createTodo, principal.getName());
        URI newRessourceUri = URI.create(request.getRequestURI() + newTodo.getId());
        return ResponseEntity.created(newRessourceUri).body(newTodo);
    }

    @PutMapping("/{id}/project")
    public ResponseEntity<Object> updateTodo(@PathVariable Long id ,@RequestBody UpdateTodoProject updateTodoProject) throws TodoNotFoundException, ProjectNotFoundException {
        todoService.updateTodoProject(id, updateTodoProject.getIdProject());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> removeTodo(@PathVariable Long id) throws TodoNotFoundException {
        todoService.removeTodo(id);
        return ResponseEntity.noContent().build();

    }
}
