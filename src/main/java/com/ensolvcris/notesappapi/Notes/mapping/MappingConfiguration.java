package com.ensolvcris.notesappapi.Notes.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("notesMappingConfiguration")
public class MappingConfiguration {

    @Bean
    public NoteMapper noteMapper() { return new NoteMapper();}

    @Bean
    public TagMapper tagMapper() { return new TagMapper();}
}
