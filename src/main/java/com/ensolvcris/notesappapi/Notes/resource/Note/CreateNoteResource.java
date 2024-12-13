package com.ensolvcris.notesappapi.Notes.resource.Note;


import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateNoteResource {
    private Integer id;
    private String title;
    private String content;
    private Integer userId;
    private Integer tagId;
}