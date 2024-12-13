package com.ensolvcris.notesappapi.Security.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private Number id;
    private String username;
    private String email;
    private String message;
}