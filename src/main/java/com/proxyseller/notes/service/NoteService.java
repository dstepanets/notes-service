package com.proxyseller.notes.service;

import com.proxyseller.notes.dto.NoteView;
import com.proxyseller.notes.model.Note;
import org.bson.types.ObjectId;

import java.util.List;

public interface NoteService {

	List<NoteView> getAllNotes();
	Note create(Note note);
	NoteView.NoteLikes likeNote(ObjectId noteId);
	NoteView.NoteLikes unLikeNote(ObjectId noteId);
}
