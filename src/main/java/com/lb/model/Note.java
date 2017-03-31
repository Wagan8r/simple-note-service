package com.lb.model;

import com.lb.entity.NoteEntity;

public class Note {
    private Integer id;
    private String body;

    public Note(){
    }

    public Note(Integer id, String body){
        setId(id);
        setBody(body);
    }

    public Note(NoteEntity noteEntity){
        setId(noteEntity.getId());
        setBody(noteEntity.getBody());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body){
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Note note = (Note) o;

        if (id != null ? !id.equals(note.id) : note.id != null) return false;
        return body != null ? body.equals(note.body) : note.body == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (body != null ? body.hashCode() : 0);
        return result;
    }
}
