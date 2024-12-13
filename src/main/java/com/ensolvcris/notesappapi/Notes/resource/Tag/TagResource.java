package com.ensolvcris.notesappapi.Notes.resource.Tag;

import com.ensolvcris.notesappapi.Security.domain.model.entity.User;
import com.ensolvcris.notesappapi.Security.resource.UserResource;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class TagResource {
    private Integer id;
    private String tagName;
    private UserResource user;
}
