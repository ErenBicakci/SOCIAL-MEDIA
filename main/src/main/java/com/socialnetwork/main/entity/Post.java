package com.socialnetwork.main.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Data
@Builder
@Document(collection = "posts")
public class Post {


    @Id
    private String id;
    private String username;
    private String imageId;
    private String description;
    private List<Comment> comments;
    private Long commentCount;
    private Date createdDate;
    private List<String> like;
    private Long likeCount;
}
