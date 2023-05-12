package com.proxyseller.notes.controller.api;

import com.proxyseller.notes.service.NoteService;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/notes")
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class NoteRestController {

	private NoteService noteService;

	@GetMapping("/{id}/likes-count")
	public ResponseEntity<Integer> getNoteLikesCount(@PathVariable("id") String noteId) {
		return new ResponseEntity<>(noteService.getNoteLikesCount(new ObjectId(noteId)), HttpStatus.OK);
	}

	@PostMapping("/{id}/like")
	public ResponseEntity<Integer> likeNote(@PathVariable("id") String noteId) {
		noteService.likeNote(new ObjectId(noteId));
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@PostMapping("/{id}/un-like")
	public ResponseEntity<Integer> unLikeNote(@PathVariable("id") String noteId) {
		noteService.unLikeNote(new ObjectId(noteId));
		return new ResponseEntity<>(noteService.getNoteLikesCount(new ObjectId(noteId)), HttpStatus.OK);
	}
}
