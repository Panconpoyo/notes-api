package com.ensolvcris.notesappapi.Notes.domain.persistence;

import com.ensolvcris.notesappapi.Notes.domain.model.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note,Integer> {

    List<Note> findByUserId(Integer userId);
    List<Note> findByArchivedAndUserId(Boolean bool, Integer userId);
    List<Note> findByTagId(Integer tagId);
}