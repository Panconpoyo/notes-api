package com.ensolvcris.notesappapi.Notes.service;

import com.ensolvcris.notesappapi.Notes.domain.model.entity.Note;
import com.ensolvcris.notesappapi.Notes.domain.model.entity.Tag;
import com.ensolvcris.notesappapi.Notes.domain.persistence.NoteRepository;
import com.ensolvcris.notesappapi.Notes.domain.persistence.TagRepository;
import com.ensolvcris.notesappapi.Notes.domain.service.NoteService;
import com.ensolvcris.notesappapi.Notes.resource.Note.CreateNoteResource;
import com.ensolvcris.notesappapi.Notes.resource.Note.UpdateNoteResource;
import com.ensolvcris.notesappapi.Security.domain.model.entity.User;
import com.ensolvcris.notesappapi.Security.domain.persistence.UserRepository;
import com.ensolvcris.notesappapi.Shared.Exception.ResourceNotFoundException;
import com.ensolvcris.notesappapi.Shared.Exception.ResourceValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class NoteServiceImpl implements NoteService {

    private static final String ENTITY = "Note";

    private final NoteRepository noteRepository;
    private final UserRepository userRepository;
    private final TagRepository tagRepository;
    private final Validator validator;

    public NoteServiceImpl(NoteRepository noteRepository, UserRepository userRepository, TagRepository tagRepository, Validator validator) {
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
        this.tagRepository = tagRepository;
        this.validator = validator;
    }

    @Override
    public List<Note> getAll() {
        return noteRepository.findAll();
    }

    @Override
    public Note getById(Integer noteId) {
        return noteRepository.findById(noteId)
                .orElseThrow(()-> new ResourceNotFoundException(ENTITY, noteId));
    }

    @Override
    public List<Note> getByUserId(Integer userId) {
        List<Note> notes = noteRepository.findByUserId(userId);

        if(notes.isEmpty())
            throw new ResourceNotFoundException(ENTITY,
                    "Not found Notes for this user");

        return notes;
    }

    @Override
    public List<Note> getByTagId(Integer tagId) {
        List<Note> notes = noteRepository.findByTagId(tagId);

        if(notes.isEmpty())
            throw new ResourceNotFoundException(ENTITY,
                    "Not found Notes with Tag: "+ tagId + " for this user");

        return notes;
    }

    @Override
    public List<Note> getActiveNotesByUserId(Integer userId) {
        List<Note> notes = noteRepository.findByArchivedAndUserId(false, userId);

        if(notes.isEmpty())
            throw new ResourceNotFoundException(ENTITY,
                    "Not found Active Notes for this user");

        return notes;
    }

    @Override
    public List<Note> getArchivedNotesByUserId(Integer userId) {
        List<Note> notes = noteRepository.findByArchivedAndUserId(true, userId);

        if(notes.isEmpty())
            throw new ResourceNotFoundException(ENTITY,
                    "Not found Archived Notes for this user");

        return notes;
    }

    @Override
    public Note create(CreateNoteResource resource) {
        Set<ConstraintViolation<CreateNoteResource>> violations = validator.validate(resource);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        Optional<User> userOptional = userRepository.findById(resource.getUserId());
        User user = userOptional.orElseThrow(() -> new NotFoundException("Not found user with ID: " + resource.getUserId()));


        Note note = new Note();
        note.setTitle(resource.getTitle());
        note.setContent(resource.getContent());
        note.setUser(user);

        if (resource.getTagId() != null) {
            Tag tag = tagRepository.findById(resource.getTagId())
                    .orElseThrow(() -> new ResourceNotFoundException("Tag", resource.getTagId()));
            note.setTag(tag);
        }

        return noteRepository.save(note);
    }

    @Override
    public Note update(Integer noteId, UpdateNoteResource resource) {
        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, noteId));

        if (resource.getTitle() != null) {
            note.setTitle(resource.getTitle());
        }
        if (resource.getContent() != null) {
            note.setContent(resource.getContent());
        }
        if (resource.getTagId() != null) {
            Optional<Tag> tagOptional = tagRepository.findById(resource.getTagId());
            Tag tag = tagOptional.orElseThrow(() -> new NotFoundException("Not found tag with ID: " + resource.getTagId()));

            note.setTag(tag);
        }else{
            note.setTag(null);
        }


        return noteRepository.save(note);
    }

    @Override
    public Note removeTag(Integer noteId) {
        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, noteId));

        note.setTag(null);

        return noteRepository.save(note);
    }

    @Override
    public Note changeNoteStatus(Integer noteId) {
        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, noteId));

        note.setArchived(!note.getArchived());

        return noteRepository.save(note);
    }


    @Override
    public ResponseEntity<?> delete(Integer noteId) {
        return noteRepository.findById(noteId).map(note -> {
            noteRepository.delete(note);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new ResourceNotFoundException(ENTITY,noteId));
    }
}