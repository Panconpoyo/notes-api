package com.ensolvcris.notesappapi.Security.domain.service;


import com.ensolvcris.notesappapi.Security.domain.model.entity.User;
import com.ensolvcris.notesappapi.Security.resource.CreateUserResource;


public interface UserService {

    User getById(Integer userId);
    User getByEmail(String email);
    User create(CreateUserResource resource);
    Boolean validatePassword(String rawPassword, String hashedPassword);

}
