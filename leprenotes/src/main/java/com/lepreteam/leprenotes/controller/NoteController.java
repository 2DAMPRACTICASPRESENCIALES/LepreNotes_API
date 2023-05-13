package com.lepreteam.leprenotes.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import org.springframework.validation.FieldError;

import com.exceptions.ErrorMessage;
import com.exceptions.NotFoundException;
import com.lepreteam.leprenotes.domain.Note;
import com.lepreteam.leprenotes.service.NoteService;

@RestController
public class NoteController {
    @Autowired
    private NoteService noteService;

    private final Logger logger = LoggerFactory.getLogger(NoteController.class);

    @GetMapping("/notes")
    public ResponseEntity<List<Note>> getNotes() {
        logger.info("Begin get notes");
        return new ResponseEntity<>(noteService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/notes/{user_id}/users")
    public ResponseEntity<List<Note>> getNotesByUserId(@PathVariable long user_id) throws NotFoundException {
        logger.info("Begin get notes by user id");
        return new ResponseEntity<>(noteService.getNotesByUserId(user_id), HttpStatus.OK);
    }

    @GetMapping("/notes/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable long id) throws NotFoundException {
        logger.info("Begin get notes by id");
        return new ResponseEntity<>(noteService.getNoteById(id), HttpStatus.OK);
    }

    @PostMapping("/notes")
    public ResponseEntity<Note> addNotes(@RequestBody Note note) throws NotFoundException {
        logger.info("Begin post notes");
        Note newNote = noteService.addNote(note);
        return ResponseEntity.status(HttpStatus.CREATED).body(newNote);
    }

    @DeleteMapping("/notes/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable long id) throws NotFoundException {
        logger.info("Begin delete notes by Id");
        noteService.deleteNote(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/notes/{id}")
    public ResponseEntity<Note> modifyNote(@PathVariable long id, @RequestBody Note note) throws NotFoundException{
        logger.info("Begin put notes by Id");
        Note newNote = noteService.updateNote(id, note);
        return ResponseEntity.status(HttpStatus.OK).body(newNote);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorMessage> handleNotFoundException(NotFoundException nfe) {
        ErrorMessage errorMessage = new ErrorMessage(404, nfe.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleBadRequestException(MethodArgumentNotValidException manve) {
        Map<String, String> errors = new HashMap<>();
        manve.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });

        ErrorMessage badRequestErrorMessage = new ErrorMessage(400, "Bad Request", errors);
        return new ResponseEntity<>(badRequestErrorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleException(Exception e) {
        ErrorMessage errorMessage = new ErrorMessage(500, "Internal Server Error");
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
