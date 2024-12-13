package com.ensolvcris.notesappapi.Notes.domain.service;

import com.ensolvcris.notesappapi.Notes.domain.model.entity.Note;
import com.ensolvcris.notesappapi.Notes.resource.Note.CreateNoteResource;
import com.ensolvcris.notesappapi.Notes.resource.Note.UpdateNoteResource;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface NoteService {
    List<Note> getAll();
    Note getById(Integer noteId);
    List<Note> getByUserId(Integer userId);
    List<Note> getByTagId(Integer tagId);
    List<Note> getActiveNotesByUserId(Integer userId);
    List<Note> getArchivedNotesByUserId(Integer userId);
    Note create(CreateNoteResource resource);
    Note update(Integer noteId, UpdateNoteResource resource);
    Note removeTag(Integer noteId);
    Note changeNoteStatus(Integer noteId);
    ResponseEntity<?> delete(Integer noteId);
}
