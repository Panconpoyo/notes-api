package com.ensolvcris.notesappapi.Notes.domain.service;

import com.ensolvcris.notesappapi.Notes.domain.model.entity.Note;
import com.ensolvcris.notesappapi.Notes.domain.model.entity.Tag;
import com.ensolvcris.notesappapi.Notes.resource.Tag.CreateTagResource;
import com.ensolvcris.notesappapi.Notes.resource.Tag.UpdateTagResource;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TagService {

    Tag getById(Integer tagId);
    List<Tag> getByUserId(Integer userId);
    Tag create(CreateTagResource resource);
    Tag update(Integer tagId, UpdateTagResource resource);
    ResponseEntity<?> delete(Integer tagId);
}
