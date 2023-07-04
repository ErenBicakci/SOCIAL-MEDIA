package com.socialnetwork.main.repository;

import com.socialnetwork.main.entity.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentRepository extends MongoRepository<Comment, String> {
}
