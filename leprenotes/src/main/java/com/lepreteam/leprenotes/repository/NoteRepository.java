package com.lepreteam.leprenotes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lepreteam.leprenotes.domain.Note;

@Repository
public interface NoteRepository extends CrudRepository<Note, Long> {
    public List<Note> findAll();

    @Query( value = "SELECT * FROM notes WHERE user_id=?", nativeQuery = true)
    List<Note> findNoteByUserId(long user_id);
}
