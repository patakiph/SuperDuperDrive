package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    private NoteMapper noteMapper;

    public NoteService (NoteMapper noteMapper){
        this.noteMapper = noteMapper;
    }

    public Integer createNote(Note note){
        return noteMapper.insertNote(new Note(null,note.getNotetitle(),note.getNotedescription(), note.getUserid()));
    }

    public List<Note> getAllNotesByUserId(Integer userid){
        return noteMapper.getAllUserNotes(userid);
    }

    public void deleteNote(Integer noteid){
        noteMapper.deleteNote(noteid);
    }
    public Integer updateNote(Note note){
        return noteMapper.updateNote(note);
    }
}
