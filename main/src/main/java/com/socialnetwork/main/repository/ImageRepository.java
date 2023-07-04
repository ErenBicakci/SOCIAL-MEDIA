package com.socialnetwork.main.repository;

import com.socialnetwork.main.entity.Image;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ImageRepository extends MongoRepository<Image,String> {
}
