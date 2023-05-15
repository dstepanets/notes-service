package com.proxyseller.notes.dto;

import com.proxyseller.notes.model.Note;
import com.proxyseller.notes.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@Component
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class NoteEntityViewMapper implements Mapper<Note, NoteView> {

	private UserService userService;

	@Override
	public NoteView mapEntityToDto(Note entity) {
		String createdAt = entity.getCreatedAt().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT));

		return NoteView.builder()
				.noteId(entity.getId())
				.authorName(entity.getAuthor().getUserName())
				.content(entity.getContent())
				.createdAt(createdAt)
				.noteLikes(
						new NoteView.NoteLikes(
								entity.getLikes().size(),
								entity.getLikes().contains(userService.getCurrentUser().getId())
						)
				)
				.build();
	}
}
