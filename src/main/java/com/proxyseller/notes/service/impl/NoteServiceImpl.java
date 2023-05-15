package com.proxyseller.notes.service.impl;

import com.proxyseller.notes.dto.NoteEntityViewMapper;
import com.proxyseller.notes.dto.NoteView;
import com.proxyseller.notes.exception.EntityNotFoundException;
import com.proxyseller.notes.model.Note;
import com.proxyseller.notes.repository.NoteRepository;
import com.proxyseller.notes.service.NoteService;
import com.proxyseller.notes.service.UserService;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class NoteServiceImpl implements NoteService {

	private NoteRepository noteRepository;
	private UserService userService;
	private NoteEntityViewMapper mapper;
	private static final String NOTE_NOT_FOUND_ERR_MSG = "Note with ID=%s not found.";

	@Override
	public List<NoteView> getAllNotes() {
		return noteRepository.findAll().stream()
				.sorted((n1, n2) -> n2.getCreatedAt().compareTo(n1.getCreatedAt()))
				.map(mapper::mapEntityToDto)
				.toList();
	}

	@Override
	public Note create(Note note) {
		var user = userService.getCurrentUser();
		note.setAuthor(new Note.Author(user.getId(), user.getName()));
		note.setCreatedAt(LocalDateTime.now());
		return noteRepository.insert(note);
	}

	@Override
	public NoteView.NoteLikes likeNote(ObjectId noteId) {
		Note note = noteRepository.findById(noteId)
				.orElseThrow(() -> new EntityNotFoundException(String.format(NOTE_NOT_FOUND_ERR_MSG, noteId)));
		note.getLikes().add(userService.getCurrentUser().getId());
		Note updatedNote = noteRepository.save(note);

		return new NoteView.NoteLikes(
				updatedNote.getLikes().size(),
				updatedNote.getLikes().contains(userService.getCurrentUser().getId())
		);
	}

	@Override
	public NoteView.NoteLikes unLikeNote(ObjectId noteId) {
		Note note = noteRepository.findById(noteId)
				.orElseThrow(() -> new EntityNotFoundException(String.format(NOTE_NOT_FOUND_ERR_MSG, noteId)));
		note.getLikes().remove(userService.getCurrentUser().getId());
		Note updatedNote = noteRepository.save(note);

		return new NoteView.NoteLikes(
				updatedNote.getLikes().size(),
				updatedNote.getLikes().contains(userService.getCurrentUser().getId())
		);
	}
}
