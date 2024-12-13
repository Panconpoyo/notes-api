package com.ensolvcris.notesappapi.Security.resource;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class UserResource {
    private Integer id;
    private String username;
    private String email;
}