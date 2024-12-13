package com.ensolvcris.notesappapi.Security.api.rest;

import com.ensolvcris.notesappapi.Security.domain.service.UserService;
import com.ensolvcris.notesappapi.Security.mapping.UserMapper;
import com.ensolvcris.notesappapi.Security.resource.UserResource;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/users", produces = "application/json")
public class UsersController {
    private final UserService userService;
    private final UserMapper mapper;

    public UsersController(UserService userService, UserMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @GetMapping("{userId}")
    public UserResource getUserById(@PathVariable Integer userId, Pageable pageable
    ) {
        return mapper.toResource(userService.getById(userId));
    }


}
