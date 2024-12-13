package com.ensolvcris.notesappapi.Notes.service;

import com.ensolvcris.notesappapi.Notes.domain.model.entity.Note;
import com.ensolvcris.notesappapi.Notes.domain.model.entity.Tag;
import com.ensolvcris.notesappapi.Notes.domain.persistence.NoteRepository;
import com.ensolvcris.notesappapi.Notes.domain.persistence.TagRepository;
import com.ensolvcris.notesappapi.Notes.domain.service.NoteService;
import com.ensolvcris.notesappapi.Notes.domain.service.TagService;
import com.ensolvcris.notesappapi.Notes.resource.Note.CreateNoteResource;
import com.ensolvcris.notesappapi.Notes.resource.Note.UpdateNoteResource;
import com.ensolvcris.notesappapi.Notes.resource.Tag.CreateTagResource;
import com.ensolvcris.notesappapi.Notes.resource.Tag.UpdateTagResource;
import com.ensolvcris.notesappapi.Security.domain.model.entity.User;
import com.ensolvcris.notesappapi.Security.domain.persistence.UserRepository;
import com.ensolvcris.notesappapi.Security.resource.UserResource;
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
public class TagServiceImpl implements TagService {

    private static final String ENTITY = "Tag";
    private final TagRepository tagRepository;
    private final UserRepository userRepository;
    private final Validator validator;

    public TagServiceImpl(TagRepository tagRepository, UserRepository userRepository, Validator validator) {
        this.tagRepository = tagRepository;
        this.userRepository = userRepository;
        this.validator = validator;
    }


    @Override
    public Tag getById(Integer tagId) {
        return tagRepository.findById(tagId)
                .orElseThrow(()-> new ResourceNotFoundException(ENTITY, tagId));
    }

    @Override
    public List<Tag> getByUserId(Integer userId) {
        List<Tag> tags = tagRepository.findByUserId(userId);

        if(tags.isEmpty())
            throw new ResourceNotFoundException(ENTITY,
                    "Not found Tags for this user");

        return tags;
    }

    @Override
    public Tag create(CreateTagResource resource) {
        Set<ConstraintViolation<CreateTagResource>> violations = validator.validate(resource);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        Optional<User> userOptional = userRepository.findById(resource.getUserId());
        User user = userOptional.orElseThrow(() -> new NotFoundException("Not found user with ID: " + resource.getUserId()));

        Tag tag = new Tag();
        tag.setTagName(resource.getTagName());
        tag.setUser(user);

        return tagRepository.save(tag);
    }

    @Override
    public Tag update(Integer tagId, UpdateTagResource resource) {
        Tag tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, tagId));

        if (resource.getTagName() != null) {
            tag.setTagName(resource.getTagName());
        }

        return tagRepository.save(tag);
    }


    @Override
    public ResponseEntity<?> delete(Integer tagId) {
        return tagRepository.findById(tagId).map(tag -> {
            tagRepository.delete(tag);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new ResourceNotFoundException(ENTITY,tagId));
    }
}
