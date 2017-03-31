package com.lb.service.impl;

import com.lb.entity.NoteEntity;
import com.lb.exception.BadRequestException;
import com.lb.model.Note;
import com.lb.repository.NotesRepository;
import com.lb.service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        return new Note(notesRepository.save(new NoteEntity(note)));
    }

    @Override
    public Note getNote(Integer id) {
        if(id == null){
            throw new BadRequestException("ID cannot be null");
        }
        return new Note(notesRepository.findOne(id));
    }

    @Override
    public List<Note> getNotes(String text) {
        if(!StringUtils.isEmpty(text)){
            text = text.trim();
            return notesRepository.findByBodyContainingText(text).stream().map(Note::new).collect(Collectors.toList());
        }
        return getNotes(notesRepository.findAll());
    }

    private List<Note> getNotes(Iterable<NoteEntity> iterable){
        List<Note> notes = new ArrayList<>();
        iterable.forEach(entity -> notes.add(new Note(entity)));
        return notes;
    }
}
