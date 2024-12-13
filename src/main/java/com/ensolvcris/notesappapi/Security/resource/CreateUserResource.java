package com.ensolvcris.notesappapi.Security.resource;

import lombok.*;


@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserResource {
    private Integer id;
    private String username;
    private String email;
    private String password;
}