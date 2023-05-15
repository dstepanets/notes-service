package com.proxyseller.notes.controller.api;

import com.proxyseller.notes.dto.NoteView;
import com.proxyseller.notes.service.NoteService;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/notes")
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class NoteRestController {

	private NoteService noteService;

	@PostMapping("/{id}/like")
	public ResponseEntity<NoteView.NoteLikes> likeNote(@PathVariable("id") String noteId) {
		return new ResponseEntity<>(noteService.likeNote(new ObjectId(noteId)), HttpStatus.OK);
	}

	@PostMapping("/{id}/un-like")
	public ResponseEntity<NoteView.NoteLikes> unLikeNote(@PathVariable("id") String noteId) {
		return new ResponseEntity<>(noteService.unLikeNote(new ObjectId(noteId)), HttpStatus.OK);
	}
}
