package com.ensolvcris.notesappapi.Security.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration("securityMappingConfiguration")
public class MappingConfiguration {

    @Bean
    public UserMapper userMapper() { return new UserMapper();}

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}