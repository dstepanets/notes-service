package com.proxyseller.notes.dto;

import lombok.*;
import org.bson.types.ObjectId;

@Data
@Builder
public class NoteView {

	private ObjectId noteId;
	private String authorName;
	private String content;
	private String createdAt;
	private NoteLikes noteLikes;

	@AllArgsConstructor
	@Getter
	@ToString
	public static class NoteLikes {

		private int likesCount;
		boolean isLiked;
	}
}
