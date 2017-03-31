package com.lb.service;

import com.lb.TestBase;
import com.lb.model.Note;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NotesServiceImplTest extends TestBase{
    @Autowired
    private NotesService notesService;

    @Test
    public void testCreateNote(){
        String noteText = "valid note";
        Note note = new Note(1, "valid note");
        Note createdNote = notesService.createNote(note);
        assertEquals(1, createdNote.getId().intValue());
        assertEquals(noteText, createdNote.getBody());
    }

    @Test
    public void testCreateNoteIdIsIgnored(){
        String noteText = "valid note";
        Integer oldId = 9;
        Note note = new Note(oldId, "valid note");
        Note createdNote = notesService.createNote(note);
        assertFalse(createdNote.getId().equals(oldId));
        assertEquals(noteText, createdNote.getBody());
    }
}
