package com.lb.service;

import com.lb.model.Note;

import java.util.List;

public interface NotesService {
    Note createNote(Note note);
    Note getNote(Integer id);
    List<Note> getNotes(String text);
}
