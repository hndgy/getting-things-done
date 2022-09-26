package fr.hndgy.todos.controllers;

import fr.hndgy.todos.commands.project.CreateProject;
import fr.hndgy.todos.entities.Project;
import fr.hndgy.todos.exceptions.UserNotFoundException;
import fr.hndgy.todos.services.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/v1/project")
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Project>> getAll(Principal principal){
        return ResponseEntity.ok(
                projectService.getProjectsByUser(principal.getName())
        );
    }

    @PostMapping("/")
    public ResponseEntity<Project> addProject(@RequestBody CreateProject project, Principal principal) throws UserNotFoundException {
        return ResponseEntity.ok(
            projectService.addProject(project.getName(), principal.getName())
        );
    }
}