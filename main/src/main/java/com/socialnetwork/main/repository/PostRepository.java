package com.socialnetwork.main.repository;

import com.socialnetwork.main.entity.Post;
import com.socialnetwork.main.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PostRepository extends MongoRepository<Post, String> {

    List<Post> findAllByUsername(String username);
}
