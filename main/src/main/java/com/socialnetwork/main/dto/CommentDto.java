package com.socialnetwork.main.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class CommentDto {

    private String id;

    private String username;

    private String postId;

    private String content;


    private Date createdDate;
}
