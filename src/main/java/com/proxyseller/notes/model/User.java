package com.proxyseller.notes.model;

import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("users")
public class User {

	@Id
	private ObjectId id;
	@NonNull
	private String name;
}
