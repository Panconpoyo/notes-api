package com.ensolvcris.notesappapi.Notes.api.rest;


import com.ensolvcris.notesappapi.Notes.domain.service.NoteService;
import com.ensolvcris.notesappapi.Notes.domain.service.TagService;
import com.ensolvcris.notesappapi.Notes.mapping.NoteMapper;
import com.ensolvcris.notesappapi.Notes.resource.Note.CreateNoteResource;
import com.ensolvcris.notesappapi.Notes.resource.Note.NoteResource;
import com.ensolvcris.notesappapi.Notes.resource.Note.UpdateNoteResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/notes", produces = "application/json")
public class NotesController {
    private final NoteService noteService;
    private final NoteMapper mapper;

    public NotesController(NoteService noteService, NoteMapper mapper) {
        this.noteService = noteService;
        this.mapper = mapper;
    }

    @GetMapping
    public Page<NoteResource> getAllNotes(
            Pageable pageable) {
        return mapper.modelListPage(noteService.getAll(), pageable);
    }

    @GetMapping("{noteId}")
    public NoteResource getNoteById(@PathVariable Integer noteId, Pageable pageable
    ) {
        return mapper.toResource(noteService.getById(noteId));
    }

    @GetMapping("byUserId/{userId}")
    public Page<NoteResource> getNotesByUserId(@PathVariable Integer userId, Pageable pageable) {
        return mapper.modelListPage(noteService.getByUserId(userId), pageable);
    }

    @GetMapping("byTagId/{tagId}")
    public Page<NoteResource> getNotesByTagId(@PathVariable Integer tagId, Pageable pageable) {
        return mapper.modelListPage(noteService.getByTagId(tagId), pageable);
    }

    @GetMapping("activeNotes/{userId}")
    public Page<NoteResource> getActiveNotesByUserId(@PathVariable Integer userId, Pageable pageable) {
        return mapper.modelListPage(noteService.getActiveNotesByUserId(userId), pageable);
    }

    @GetMapping("archivedNotes/{userId}")
    public Page<NoteResource> getArchivedNotesByUserId(@PathVariable Integer userId, Pageable pageable) {
        return mapper.modelListPage(noteService.getArchivedNotesByUserId(userId), pageable);
    }


    @PostMapping
    public ResponseEntity<NoteResource> createNote(@RequestBody CreateNoteResource resource) {
        return new ResponseEntity<>(mapper.toResource(noteService.create(resource)), HttpStatus.CREATED);
    }

    @PatchMapping("{noteId}")
    public ResponseEntity<NoteResource> patchNote(
            @PathVariable Integer noteId,
            @RequestBody UpdateNoteResource request) {

        return new  ResponseEntity<>(mapper.toResource(noteService.update(noteId,request)), HttpStatus.CREATED);
    }

    @PatchMapping("removeTag/{noteId}")
    public ResponseEntity<NoteResource> removeTag(
            @PathVariable Integer noteId) {
        return new  ResponseEntity<>(mapper.toResource(noteService.removeTag(noteId)), HttpStatus.CREATED);
    }

    @PatchMapping("changeStatus/{noteId}")
    public ResponseEntity<NoteResource> changeNoteStatus(
            @PathVariable Integer noteId) {

        return new  ResponseEntity<>(mapper.toResource(noteService.changeNoteStatus(noteId)), HttpStatus.CREATED);
    }

    @DeleteMapping("{noteId}")
    public ResponseEntity<?> deleteNote(@PathVariable Integer noteId) {
        return noteService.delete(noteId);
    }
}
