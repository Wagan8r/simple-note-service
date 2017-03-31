package com.lb.repository;

import com.lb.exception.NoteNotFoundException;
import com.lb.model.Note;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Component
public class NotesRepository {
    //making this all in-memory for now
    private Map<Integer, Note> notes = new HashMap<>();
    private AtomicInteger counter = new AtomicInteger(1);

    public Note save(Note note){
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

    public List<Note> getAll(){
        return new ArrayList<>(notes.values());
    }

    public List<Note> getAll(String text){
        return notes.values().stream().filter(note -> note.getBody().toLowerCase().contains(text.toLowerCase())).collect(Collectors.toList());
    }
}
