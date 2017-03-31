package com.lb.entity;

import com.lb.model.Note;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class NoteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String body;

    public Integer getId() {
        return id;
    }

    public NoteEntity(){
    }

    public NoteEntity(Note note){
        body = note.getBody();
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
