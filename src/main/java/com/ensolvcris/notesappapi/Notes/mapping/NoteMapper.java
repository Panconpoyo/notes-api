package com.ensolvcris.notesappapi.Notes.mapping;


import com.ensolvcris.notesappapi.Notes.domain.model.entity.Note;
import com.ensolvcris.notesappapi.Notes.resource.Note.CreateNoteResource;
import com.ensolvcris.notesappapi.Notes.resource.Note.NoteResource;
import com.ensolvcris.notesappapi.Notes.resource.Note.UpdateNoteResource;
import com.ensolvcris.notesappapi.Shared.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class NoteMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public NoteResource toResource(Note model) {
        return mapper.map(model, NoteResource.class);
    }

    public Note toModel(CreateNoteResource resource) {
        return mapper.map(resource, Note.class);
    }

    public Note toModel(UpdateNoteResource resource) {
        return mapper.map(resource, Note.class);
    }

    public Page<NoteResource> modelListPage(List<Note> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, NoteResource.class), pageable, modelList.size());
    }
}