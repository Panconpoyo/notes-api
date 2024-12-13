package com.ensolvcris.notesappapi.Security.domain.persistence;

import com.ensolvcris.notesappapi.Security.domain.model.entity.User;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void initializeData() {
        if (userRepository.count() == 0) {
            User adminUser = new User();
            adminUser.setUsername("admin");
            adminUser.setEmail("admin@example.com");
            adminUser.setPassword(passwordEncoder.encode("admin"));

            userRepository.save(adminUser);

            System.out.println("Admin user created: admin@example.com / admin");
        } else {
            System.out.println("Users already exist, skipping admin creation.");
        }
    }
}