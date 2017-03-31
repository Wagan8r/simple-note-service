package com.lb.repository;

import com.lb.entity.NoteEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NotesRepository extends CrudRepository<NoteEntity, Integer>{

    @Query("SELECT n FROM NoteEntity n WHERE LOWER(n.body) LIKE LOWER(CONCAT('%', :text, '%'))")
    List<NoteEntity> findByBodyContainingText(@Param("text") String text);
}
