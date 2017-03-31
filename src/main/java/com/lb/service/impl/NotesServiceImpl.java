package com.lb.service.impl;

import com.lb.exception.BadRequestException;
import com.lb.model.Note;
import com.lb.repository.NotesRepository;
import com.lb.service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class NotesServiceImpl implements NotesService{
    @Autowired
    private NotesRepository notesRepository;

    @Override
    public Note createNote(Note note) {
        if(StringUtils.isEmpty(note.getBody())){
            throw new BadRequestException("Note body cannot be empty");
        }
        note.setBody(note.getBody().trim());
        return notesRepository.save(note);
    }

    @Override
    public Note getNote(Integer id) {
        if(id == null){
            throw new BadRequestException("ID cannot be null");
        }
        return notesRepository.read(id);
    }

    @Override
    public List<Note> getNotes(String text) {
        text = text.trim();
        if(!StringUtils.isEmpty(text)){
            return notesRepository.getAll(text);
        }
        return notesRepository.getAll();
    }
}
