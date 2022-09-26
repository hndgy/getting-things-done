package fr.hndgy.todos.services;

import fr.hndgy.todos.commands.todo.CreateTodo;
import fr.hndgy.todos.commands.todo.UpdateTodo;
import fr.hndgy.todos.entities.AppUser;
import fr.hndgy.todos.entities.Todo;
import fr.hndgy.todos.exceptions.ProjectNotFoundException;
import fr.hndgy.todos.exceptions.TodoNotFoundException;
import fr.hndgy.todos.exceptions.UserNotFoundException;
import fr.hndgy.todos.repositories.ProjectRepository;
import fr.hndgy.todos.repositories.TodoRepository;
import fr.hndgy.todos.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(rollbackOn = Exception.class)
public class TodoService {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    public TodoService(TodoRepository todoRepository, UserRepository userRepository,
            ProjectRepository projectRepository) {
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
    }


    public List<Todo> getListTodosByUser(String mail){
        return todoRepository.findAllByOwner(mail);
    }

    public List<Todo> getListTodosInboxByUser(String mail){
        return todoRepository.findByProjectNullAndOwner(mail);
    }
    public List<Todo> getListTodosASAPByUser(String mail){
        return todoRepository.findByProjectNotNullAndOwner(mail);
    }
    public List<Todo> getListTodosPlannedByUser(String mail){
        return todoRepository.findByDueDateIsNotNullAndOwnerAndOwner(mail);
    }

    public Todo createTodo(CreateTodo createTodo, String mailCreator) throws UserNotFoundException {
        Todo todo = new Todo();

        AppUser creator = userRepository.findByEmail(mailCreator)
                .orElseThrow(UserNotFoundException::new);

        todo.setTitle(createTodo.getTitle());
        todo.setPriority(createTodo.isPriority());
        todo.setCompleted(false);
        todo.setDueDate(createTodo.getDueDate());
        todo.setDateCreated(LocalDateTime.now());
        todo.setCreatedBy(creator);

        todoRepository.save(todo);

        return todo;
    }

    public void updateTodo(Long id, UpdateTodo updateTodo) throws TodoNotFoundException {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(TodoNotFoundException::new);

        todo.setDueDate(updateTodo.getDueDate());
        todo.setTitle(updateTodo.getTitle());

        todoRepository.save(todo);

    }

    public void removeTodo(Long id) throws TodoNotFoundException {
        todoRepository.findById(id).orElseThrow(TodoNotFoundException::new);
        todoRepository.deleteById(id);
    }

    public Todo getTodo(Long id) throws TodoNotFoundException {
        return this.todoRepository.findById(id).orElseThrow(TodoNotFoundException::new);
    }

    public void assignProject(Long id, Long projectId) throws TodoNotFoundException, ProjectNotFoundException {
        var todo = this.todoRepository.findById(id).orElseThrow(TodoNotFoundException::new);
        var project = projectRepository.findById(id).orElseThrow(ProjectNotFoundException::new);
        todo.setProject(project);
        todoRepository.save(todo);
    }


    public void completeTodo(Long id, boolean completed) throws TodoNotFoundException {
        var todo = this.todoRepository.findById(id).orElseThrow(TodoNotFoundException::new);
        todo.setCompleted(completed);
        todoRepository.save(todo);
    }
}
