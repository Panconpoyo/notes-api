package com.ensolvcris.notesappapi.Security.api.rest;

import com.ensolvcris.notesappapi.Security.domain.model.entity.User;
import com.ensolvcris.notesappapi.Security.domain.service.UserService;
import com.ensolvcris.notesappapi.Security.mapping.UserMapper;
import com.ensolvcris.notesappapi.Security.resource.CreateUserResource;
import com.ensolvcris.notesappapi.Security.resource.LoginRequest;
import com.ensolvcris.notesappapi.Security.resource.LoginResponse;
import com.ensolvcris.notesappapi.Security.resource.UserResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserService userService;

    private final UserMapper mapper;

    public AuthController(UserService userService, UserMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Validated @RequestBody LoginRequest request) {

        User user = userService.getByEmail(request.getEmail());

        if (!userService.validatePassword(request.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        LoginResponse response = new LoginResponse();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setMessage("Login successful");

        return ResponseEntity.ok(response);
    }

    @PostMapping("/signup")
    public ResponseEntity<UserResource> createUser(@RequestBody CreateUserResource resource) {
        return new ResponseEntity<>(mapper.toResource(userService.create(resource)), HttpStatus.CREATED);
    }
}