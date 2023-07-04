package com.socialnetwork.main.dto;

import com.socialnetwork.main.entity.Comment;
import com.socialnetwork.main.entity.Image;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class PostDto {

    private String id;
    private String username;
    private String imageId;
    private String description;
    private List<CommentDto> comments;
    private Date createdDate;
    private ImageDto image;
    private List<String> like;
    private Long likeCount;
    private Long commentCount;
}
