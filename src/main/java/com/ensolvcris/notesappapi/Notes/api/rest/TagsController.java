package com.ensolvcris.notesappapi.Notes.api.rest;

import com.ensolvcris.notesappapi.Notes.domain.service.TagService;
import com.ensolvcris.notesappapi.Notes.mapping.TagMapper;
import com.ensolvcris.notesappapi.Notes.resource.Tag.CreateTagResource;
import com.ensolvcris.notesappapi.Notes.resource.Tag.TagResource;
import com.ensolvcris.notesappapi.Notes.resource.Tag.UpdateTagResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/v1/tags", produces = "application/json")
public class TagsController {
    private final TagService tagService;
    private final TagMapper mapper;

    public TagsController(TagService tagService, TagMapper mapper) {
        this.tagService = tagService;
        this.mapper = mapper;
    }

    @GetMapping("{tagId}")
    public TagResource getTagById(@PathVariable Integer tagId, Pageable pageable
    ) {
        return mapper.toResource(tagService.getById(tagId));
    }

    @GetMapping("byUserId/{userId}")
    public Page<TagResource> getTagsByUserId(@PathVariable Integer userId, Pageable pageable) {
        return mapper.modelListPage(tagService.getByUserId(userId), pageable);
    }

    @PostMapping
    public ResponseEntity<TagResource> createTag(@RequestBody CreateTagResource resource) {
        return new ResponseEntity<>(mapper.toResource(tagService.create(resource)), HttpStatus.CREATED);
    }

    @PatchMapping("{tagId}")
    public ResponseEntity<TagResource> patchTag(
            @PathVariable Integer tagId,
            @RequestBody UpdateTagResource request) {

        return new  ResponseEntity<>(mapper.toResource(tagService.update(tagId,request)), HttpStatus.CREATED);
    }

    @DeleteMapping("{tagId}")
    public ResponseEntity<?> deleteTag(@PathVariable Integer tagId) {
        return tagService.delete(tagId);
    }
}
