package com.lb.controller;

import com.lb.model.Note;
import com.lb.service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NotesController {

    @Autowired
    private NotesService notesService;

    @RequestMapping(method = RequestMethod.POST)
    public Note createNote(@RequestBody Note note){
        return notesService.createNote(note);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Note getNote(@PathVariable(value = "id") Integer id){
        return notesService.getNote(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Note> getNotes(@RequestParam(value = "query", required = false) String text){
        return notesService.getNotes(text);
    }
}
