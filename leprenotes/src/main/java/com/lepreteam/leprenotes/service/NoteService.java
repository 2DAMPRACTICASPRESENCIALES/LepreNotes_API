package com.lepreteam.leprenotes.service;

import java.util.List;

import com.exceptions.NotFoundException;
import com.lepreteam.leprenotes.domain.Note;

public interface NoteService {
    List<Note> findAll();
    List<Note> getNotesByUserId(long user_id);
    Note getNoteById(long note_id) throws NotFoundException;
    Note addNote(Note note);
    Note updateNote(long note_id, Note note) throws NotFoundException;
    void deleteNote(long note_id) throws NotFoundException;
}
