package com.lb.controller;

import com.lb.model.Note;
import com.lb.repository.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NotesController {

    @Autowired
    NotesRepository notesRepository;

    @RequestMapping(method = RequestMethod.POST)
    public Note createNote(@RequestBody Note note){
        return notesRepository.create(note);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Note getNote(@PathVariable(value = "id") Integer id){
        return notesRepository.read(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Note> getNotes(@RequestParam(value = "query", required = false) String text){
        return notesRepository.getAll(text);
    }
}
