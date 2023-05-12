package com.proxyseller.notes.service.impl;

import com.proxyseller.notes.exception.EntityNotFoundException;
import com.proxyseller.notes.model.Note;
import com.proxyseller.notes.repository.NoteRepository;
import com.proxyseller.notes.service.NoteService;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class NoteServiceImpl implements NoteService {

	private NoteRepository noteRepository;

	@Override
	public List<Note> getAllNotes() {
		return noteRepository.findAll().stream()
				.sorted((n1, n2) -> n2.getCreatedAt().compareTo(n1.getCreatedAt()))
				.collect(Collectors.toList());
	}

	@Override
	public Note create(Note note) {
		note.setCreatedAt(LocalDateTime.now());
		note.setAuthor(new Note.Author(ObjectId.get(), "Author Name"));	// TODO impl authentication
		return noteRepository.insert(note);
	}

	@Override
	public int getNoteLikesCount(ObjectId noteId) {
		return noteRepository.findById(noteId)
				.map(note -> note.getLikes().size())
				.orElseThrow(() -> new EntityNotFoundException(String.format("Note with ID=%s not found.", noteId)));
	}

	@Override
	public void likeNote(ObjectId noteId) {
		Note note = noteRepository.findById(noteId)
				.orElseThrow(() -> new EntityNotFoundException(String.format("Note with ID=%s not found.", noteId)));

		note.getLikes().add(ObjectId.get());	// TODO TMP
		noteRepository.save(note);
	}

	@Override
	public void unLikeNote(ObjectId noteId) {
		Note note = noteRepository.findById(noteId)
				.orElseThrow(() -> new EntityNotFoundException(String.format("Note with ID=%s not found.", noteId)));

		note.getLikes().remove(ObjectId.get());		// TODO TMP
		noteRepository.save(note);
	}
}
