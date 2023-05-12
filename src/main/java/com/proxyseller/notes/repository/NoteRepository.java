package com.proxyseller.notes.repository;

import com.proxyseller.notes.model.Note;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NoteRepository extends MongoRepository<Note, ObjectId> {
}
