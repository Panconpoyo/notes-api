package com.ensolvcris.notesappapi.Security.service;


import com.ensolvcris.notesappapi.Security.domain.model.entity.User;
import com.ensolvcris.notesappapi.Security.domain.persistence.UserRepository;
import com.ensolvcris.notesappapi.Security.domain.service.UserService;
import com.ensolvcris.notesappapi.Security.resource.CreateUserResource;
import com.ensolvcris.notesappapi.Shared.Exception.ResourceNotFoundException;
import com.ensolvcris.notesappapi.Shared.Exception.ResourceValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private static final String ENTITY = "User";

    private final UserRepository userRepository;
    private final Validator validator;

    private final PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository userRepository, Validator validator, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.validator = validator;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User getById(Integer userId) {
        return userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException(ENTITY, userId));
    }

    @Override
    public User create(CreateUserResource resource) {
        Set<ConstraintViolation<CreateUserResource>> violations = validator.validate(resource);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        User user = new User();
        user.setUsername(resource.getUsername());
        user.setEmail(resource.getEmail());
        user.setPassword(passwordEncoder.encode(resource.getPassword()));

        return userRepository.save(user);
    }

    public User getByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(()-> new ResourceNotFoundException(ENTITY, email));
    }

    public Boolean validatePassword(String rawPassword, String hashedPassword) {
        return passwordEncoder.matches(rawPassword, hashedPassword);
    }
}