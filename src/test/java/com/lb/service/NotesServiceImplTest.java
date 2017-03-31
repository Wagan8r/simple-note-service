package com.lb.service;

import com.lb.TestBase;
import com.lb.exception.BadRequestException;
import com.lb.exception.NoteNotFoundException;
import com.lb.model.Note;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class NotesServiceImplTest extends TestBase{
    private static final String NOTE_TEXT = "And that's why you always leave a note.";

    @Autowired
    private NotesService notesService;

    @Test
    public void testCreateNote(){
        Note createdNote = notesService.createNote(new Note(NOTE_TEXT));
        assertEquals(NOTE_TEXT, createdNote.getBody());
    }

    @Test
    public void testCreateNoteIdIsIgnored(){
        Integer oldId = 999;
        Note createdNote = notesService.createNote(new Note(oldId, NOTE_TEXT));
        assertFalse(createdNote.getId().equals(oldId));
    }

    @Test
    public void testCreateNoteWhitespaceTrimmed(){
        Note createdNote = notesService.createNote(new Note("    " + NOTE_TEXT + "  "));
        assertEquals(NOTE_TEXT, createdNote.getBody());
    }

    @Test
    public void testGetNoteById(){
        Note note = notesService.createNote(new Note(NOTE_TEXT));
        Note noteById = notesService.getNote(note.getId());
        assertEquals(note.getId(), noteById.getId());
        assertEquals(note.getBody(), noteById.getBody());
    }

    @Test(expected = BadRequestException.class)
    public void testCreateNoteWithEmptyBody(){
        notesService.createNote(new Note(""));
    }

    @Test(expected = BadRequestException.class)
    public void testCreateNoteWithNullBody(){
        notesService.createNote(new Note());
    }

    @Test(expected = NoteNotFoundException.class)
    public void testGetNoteInvalidId(){
        notesService.getNote(1000);
    }

    @Test
    public void testGetAllNotes(){
        createNotes();
        assertEquals(3, notesService.getNotes(null).size());
        assertEquals(3, notesService.getNotes("").size());
        assertEquals(1, notesService.getNotes("money").size());
        assertEquals(1, notesService.getNotes("    banana sta ").size());
        assertEquals(1, notesService.getNotes("MIstaKe").size());
        assertEquals(2, notesService.getNotes("always").size());
    }

    private void createNotes(){
        notesService.createNote(new Note(NOTE_TEXT));
        notesService.createNote(new Note("There's always money in the banana stand!"));
        notesService.createNote(new Note("I've made a huge mistake."));
    }
}
