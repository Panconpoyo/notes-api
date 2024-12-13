package com.ensolvcris.notesappapi.Security.mapping;


import com.ensolvcris.notesappapi.Security.domain.model.entity.User;
import com.ensolvcris.notesappapi.Security.resource.CreateUserResource;
import com.ensolvcris.notesappapi.Security.resource.UpdateUserResource;
import com.ensolvcris.notesappapi.Security.resource.UserResource;
import com.ensolvcris.notesappapi.Shared.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class UserMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public UserResource toResource(User model) {
        return mapper.map(model, UserResource.class);
    }

    public User toModel(CreateUserResource resource) {
        return mapper.map(resource, User.class);
    }

    public User toModel(UpdateUserResource resource) {
        return mapper.map(resource, User.class);
    }

    public Page<UserResource> modelListPage(List<User> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, UserResource.class), pageable, modelList.size());
    }
}