package com.proxyseller.notes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Document("notes")
public class Note {

	@Id
	private ObjectId id;
	private Author author;
	private String content;
	private LocalDateTime createdAt;

	/** User IDs */
	@ToString.Exclude
	private Set<ObjectId> likes = new HashSet<>();

	@Data
	@AllArgsConstructor
	public static class Author {
		private ObjectId userId;
		private String userName;
	}
}
