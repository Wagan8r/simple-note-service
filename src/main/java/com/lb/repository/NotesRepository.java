package com.lb.repository;

import com.lb.exception.BadRequestException;
import com.lb.exception.NoteNotFoundException;
import com.lb.model.Note;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Component
public class NotesRepository {
    //making this all in-memory for now
    private Map<Integer, Note> notes = new HashMap<>();
    private AtomicInteger counter = new AtomicInteger(1);

    public Note create(Note note){
        if(StringUtils.isEmpty(note.getBody())){
            throw new BadRequestException("Note body cannot be empty");
        }
        Note createdNote = new Note(counter.getAndIncrement(), note.getBody());
        notes.put(createdNote.getId(), createdNote);
        return createdNote;
    }

    public Note read(Integer id){
        if(!notes.containsKey(id)){
            throw new NoteNotFoundException("No note exists for ID:" + id);
        }
        return notes.get(id);
    }

    public List<Note> getAll(String text){
        if(!StringUtils.isEmpty(text)){
            return notes.values().stream().filter(note -> note.getBody().contains(text)).collect(Collectors.toList());
        }
        return new ArrayList<>(notes.values());
    }
}
