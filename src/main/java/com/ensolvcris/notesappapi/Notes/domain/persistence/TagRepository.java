package com.ensolvcris.notesappapi.Notes.domain.persistence;

import com.ensolvcris.notesappapi.Notes.domain.model.entity.Note;
import com.ensolvcris.notesappapi.Notes.domain.model.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag,Integer> {

    List<Tag> findByUserId(Integer userId);

}