package com.ensolvcris.notesappapi.Notes.resource.Note;

import lombok.*;

@Getter
@Setter
public class UpdateNoteResource {
    private String title;
    private String content;
    private Integer tagId;
}
