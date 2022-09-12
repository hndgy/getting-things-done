package com.example.todos.repositories;

import fr.hndgy.todos.entities.AppUser;
import fr.hndgy.todos.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;


    @Test
    public void testCreateUser(){
        var passwordEncoder = new BCryptPasswordEncoder();
        var rawPassword = "test1234";
        var encodedPassword = passwordEncoder.encode(rawPassword);

        var newUser = new AppUser();
        newUser.setEmail("test@test.fr");
        newUser.setPassword(encodedPassword);

        var savedUser = userRepository.save(newUser);

        assertNotNull(savedUser);
        assertTrue(savedUser.getId() > 0);
    }
}