package com.socialnetwork.main.controller;

import com.socialnetwork.main.dto.CommentDto;
import com.socialnetwork.main.dto.PostDto;
import com.socialnetwork.main.log.CustomLogInfo;
import com.socialnetwork.main.service.PostService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/post")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @CustomLogInfo
    @PostMapping
    public PostDto save(@RequestBody PostDto postDto){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        postDto.setUsername(auth.getName());

        return postService.postSave(postDto);
    }

    @CustomLogInfo
    @GetMapping
    public PostDto getPost(@RequestParam String id){
        return postService.getPost(id);
    }

    @CustomLogInfo
    @GetMapping("/all")
    public List<PostDto> getAllPostFromUser(@RequestParam String username){
        return postService.getAllPostsFromUser(username);
    }

    @CustomLogInfo
    @PostMapping("/like")
    public void likePost(@RequestParam String id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        postService.likePost(auth.getName(),id);
    }

    @CustomLogInfo
    @PostMapping("/comment")
    public void writeComment(@RequestBody CommentDto commentDto){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        commentDto.setUsername(auth.getName());
        postService.commentToPost(commentDto);
    }

}
