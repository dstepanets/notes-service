package com.proxyseller.notes.model;

import lombok.Data;
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
	private ObjectId userId;
	private String content;
	private LocalDateTime createdAt;

	/** User IDs */
	private Set<ObjectId> likes = new HashSet<>();
}
