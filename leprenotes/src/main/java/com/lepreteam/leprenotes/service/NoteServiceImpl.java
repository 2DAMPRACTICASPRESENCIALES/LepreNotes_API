package com.lepreteam.leprenotes.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exceptions.NotFoundException;
import com.lepreteam.leprenotes.domain.Note;
import com.lepreteam.leprenotes.repository.NoteRepository;

@Service
public class NoteServiceImpl implements NoteService {
    @Autowired
    NoteRepository noteRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Note> findAll() {
        return noteRepository.findAll();
    }

    @Override
    public List<Note> getNotesByUserId(long user_id) {
        return noteRepository.findNoteByUserId(user_id);
    }

    @Override
    public Note getNoteById(long note_id) throws NotFoundException {
        Note note = noteRepository.findById(note_id)
                    .orElseThrow(() -> new NotFoundException(new Note()));
        return note;
    }

    @Override
    public Note addNote(Note note) {
        return noteRepository.save(note);
    }

    @Override
    public Note updateNote(long note_id, Note note) throws NotFoundException {
        Note oldNote = noteRepository.findById(note_id)
        .orElseThrow(() -> new NotFoundException(new Note()));

        modelMapper.map(note, oldNote);
        
        return noteRepository.save(oldNote);
    }

    @Override
    public void deleteNote(long note_id) throws NotFoundException {
        Note note = noteRepository.findById(note_id)
        .orElseThrow(() -> new NotFoundException(new Note()));

        noteRepository.delete(note);
    }
    
}
