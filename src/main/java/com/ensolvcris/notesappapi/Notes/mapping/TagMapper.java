package com.ensolvcris.notesappapi.Notes.mapping;

import com.ensolvcris.notesappapi.Notes.domain.model.entity.Note;
import com.ensolvcris.notesappapi.Notes.domain.model.entity.Tag;
import com.ensolvcris.notesappapi.Notes.resource.Note.CreateNoteResource;
import com.ensolvcris.notesappapi.Notes.resource.Note.NoteResource;
import com.ensolvcris.notesappapi.Notes.resource.Note.UpdateNoteResource;
import com.ensolvcris.notesappapi.Notes.resource.Tag.CreateTagResource;
import com.ensolvcris.notesappapi.Notes.resource.Tag.TagResource;
import com.ensolvcris.notesappapi.Notes.resource.Tag.UpdateTagResource;
import com.ensolvcris.notesappapi.Shared.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class TagMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public TagResource toResource(Tag model) {
        return mapper.map(model, TagResource.class);
    }

    public Tag toModel(CreateTagResource resource) {
        return mapper.map(resource, Tag.class);
    }

    public Tag toModel(UpdateTagResource resource) {
        return mapper.map(resource, Tag.class);
    }

    public Page<TagResource> modelListPage(List<Tag> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, TagResource.class), pageable, modelList.size());
    }
}