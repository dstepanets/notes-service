package com.proxyseller.notes.controller;

import com.proxyseller.notes.dto.NoteView;
import com.proxyseller.notes.model.Note;
import com.proxyseller.notes.service.NoteService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("notes")
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class NoteController {

	private static final String NOTES = "Notes";
	private NoteService noteService;

	@GetMapping
	public String getNotesList(Model model) {
		List<NoteView> allNotes = noteService.getAllNotes();
		model.addAttribute("allNotes", allNotes);
		model.addAttribute("newNote", new Note());
		model.addAttribute(HomeController.PAGE_TITLE_ATTRIBUTE, NOTES);
		return "notes";
	}

	@PostMapping
	public String createNote(@ModelAttribute(name="newNote") Note note) {
		noteService.create(note);
		return "redirect:/notes";
	}
}
