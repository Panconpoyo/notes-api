package com.ensolvcris.notesappapi.Notes.resource.Note;


import com.ensolvcris.notesappapi.Notes.domain.model.entity.Tag;
import com.ensolvcris.notesappapi.Security.domain.model.entity.User;
import com.ensolvcris.notesappapi.Security.resource.UserResource;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class NoteResource {
    private Integer id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;;
    private UserResource user;
    private Tag tag;
    private Boolean archived;
}