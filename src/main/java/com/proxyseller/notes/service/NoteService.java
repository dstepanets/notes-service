package com.proxyseller.notes.service;

import com.proxyseller.notes.model.Note;
import org.bson.types.ObjectId;

import java.util.List;

public interface NoteService {

	List<Note> getAllNotes();
	Note create(Note note);
	int getNoteLikesCount(ObjectId noteId);
}
