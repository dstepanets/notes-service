package com.proxyseller.notes.service.impl;

import com.proxyseller.notes.config.IAuthenticationFacade;
import com.proxyseller.notes.exception.EntityNotFoundException;
import com.proxyseller.notes.model.Note;
import com.proxyseller.notes.model.User;
import com.proxyseller.notes.repository.NoteRepository;
import com.proxyseller.notes.service.NoteService;
import com.proxyseller.notes.service.UserService;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class NoteServiceImpl implements NoteService {

	private NoteRepository noteRepository;
	private IAuthenticationFacade authFacade;
	private UserService userService;
	private static final String NOTE_NOT_FOUND_ERR_MSG = "Note with ID=%s not found.";

	@Override
	public List<Note> getAllNotes() {
		return noteRepository.findAll().stream()
				.sorted((n1, n2) -> n2.getCreatedAt().compareTo(n1.getCreatedAt()))
				.collect(Collectors.toList());
	}

	@Override
	public Note create(Note note) {
		var authentication = authFacade.getAuthentication();
		if (authentication instanceof AnonymousAuthenticationToken) {
			User visitor = userService.getVisitorUser();
			note.setAuthor(new Note.Author(visitor.getId(), visitor.getName()));
		} else {
			var user = userService.findByName(authentication.getName())
					.orElseThrow(() -> new UsernameNotFoundException("User Name: " + authentication.getName()));
			note.setAuthor(new Note.Author(user.getId(), user.getName()));
		}

		note.setCreatedAt(LocalDateTime.now());
		return noteRepository.insert(note);
	}

	@Override
	public int getNoteLikesCount(ObjectId noteId) {
		return noteRepository.findById(noteId)
				.map(note -> note.getLikes().size())
				.orElseThrow(() -> new EntityNotFoundException(String.format(NOTE_NOT_FOUND_ERR_MSG, noteId)));
	}

	@Override
	public void likeNote(ObjectId noteId) {
		Note note = noteRepository.findById(noteId)
				.orElseThrow(() -> new EntityNotFoundException(String.format(NOTE_NOT_FOUND_ERR_MSG, noteId)));

		note.getLikes().add(ObjectId.get());	// TODO TMP
		noteRepository.save(note);
	}

	@Override
	public void unLikeNote(ObjectId noteId) {
		Note note = noteRepository.findById(noteId)
				.orElseThrow(() -> new EntityNotFoundException(String.format(NOTE_NOT_FOUND_ERR_MSG, noteId)));

		note.getLikes().remove(ObjectId.get());		// TODO TMP
		noteRepository.save(note);
	}
}
