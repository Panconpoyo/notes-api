package com.ensolvcris.notesappapi.Notes.resource.Tag;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateTagResource {
    private Integer id;
    private String tagName;
    private Integer userId;
}
