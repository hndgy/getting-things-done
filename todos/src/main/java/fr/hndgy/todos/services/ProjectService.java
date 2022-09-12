package fr.hndgy.todos.services;

import fr.hndgy.todos.commands.project.CreateProject;
import fr.hndgy.todos.entities.Project;
import fr.hndgy.todos.exceptions.UserNotFoundException;
import fr.hndgy.todos.repositories.ProjectRepository;
import fr.hndgy.todos.repositories.UserRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public ProjectService(ProjectRepository projectRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }


    public List<Project> getProjectsByUser(String email){
        return projectRepository.findAllByUser(email);
    }

    public Project addProject(CreateProject createProject, String email) throws UserNotFoundException {
        var project = new Project();
        project.setName(createProject.getName());
        project.setCreatedBy(
            userRepository.findByEmail(email).orElseThrow(()-> new UserNotFoundException())
        );
        return projectRepository.save(project);
    }
}
