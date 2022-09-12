package fr.hndgy.todos;

import fr.hndgy.todos.entities.AppUser;
import fr.hndgy.todos.entities.Project;
import fr.hndgy.todos.entities.UserRole;
import fr.hndgy.todos.repositories.ProjectRepository;
import fr.hndgy.todos.repositories.RoleRepository;
import fr.hndgy.todos.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class TodosApplication {
    public static void main(String[] args) {
        SpringApplication.run(TodosApplication.class, args);
    }
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ProjectRepository projectRepository;

    public TodosApplication(UserRepository userRepository, RoleRepository roleRepository, ProjectRepository projectRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.projectRepository = projectRepository;
    }


    @Bean
    public CommandLineRunner run() throws Exception {
        return args -> {

            var userRole = new UserRole();
            userRole.setRoleName("USER");
            var savedUserRole = roleRepository.save(userRole);

            var passwordEncoder = new BCryptPasswordEncoder();
            var rawPassword = "1234";
            var encodedPassword = passwordEncoder.encode(rawPassword);

            var newUser = new AppUser();
            newUser.setEmail("test@test.fr");
            newUser.setPassword(encodedPassword);
            newUser.setRoles(new ArrayList<>());
            newUser.getRoles().add(savedUserRole);


            var newUser2 = new AppUser();
            newUser2.setEmail("test3@test.fr");
            newUser2.setPassword(encodedPassword);
            newUser2.setRoles(new ArrayList<>());
            newUser2.getRoles().add(savedUserRole);

            var savedUser = userRepository.save(newUser);
            userRepository.save(newUser2);


            var projet1 = new Project();
            projet1.setName("Projet1");
            projet1.setCreatedBy(savedUser);
            var projet2 = new Project();
            projet2.setName("Projet2");
            projet2.setCreatedBy(savedUser);

            projectRepository.save(projet1);
            projectRepository.save(projet2);

        };

    }
}
