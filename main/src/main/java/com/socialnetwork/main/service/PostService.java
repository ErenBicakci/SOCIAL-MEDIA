package com.socialnetwork.main.service;

import com.socialnetwork.main.dto.CommentDto;
import com.socialnetwork.main.dto.ImageDto;
import com.socialnetwork.main.dto.PostDto;
import com.socialnetwork.main.entity.Comment;
import com.socialnetwork.main.entity.Image;
import com.socialnetwork.main.entity.Post;
import com.socialnetwork.main.exception.GenericException;
import com.socialnetwork.main.repository.CommentRepository;
import com.socialnetwork.main.repository.ImageRepository;
import com.socialnetwork.main.repository.PostRepository;
import lombok.extern.log4j.Log4j2;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Log4j2
@Service
public class PostService {

    private final PostRepository postRepository;
    private final ImageRepository imageRepository;

    private final CommentRepository commentRepository;

    public PostService(PostRepository postRepository, ImageRepository imageRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.imageRepository = imageRepository;
        this.commentRepository = commentRepository;
    }


    public PostDto postSave(PostDto postDto) {
        byte[] imageData;
        try {
            imageData = Base64.getMimeDecoder().decode(postDto.getImage().getData());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Image data is not base64 encoded");
        }
        Image image = Image.builder()
                .contentType(postDto.getImage().getType())
                .data(imageData)
                .deleted(false)
                .name(postDto.getImage().getName())
                .build();

        imageRepository.save(image);


        Post post = Post.builder()
                .username(postDto.getUsername())
                .description(postDto.getDescription())
                .createdDate(new Date())
                .imageId(image.getId())
                .like(new ArrayList<>())
                .likeCount(0L)
                .comments(new ArrayList<>())
                .commentCount(0L)
                .build();
        postRepository.save(post);

        return PostDto.builder()
                .comments(post.getComments()
                        .stream()
                        .map(comment -> CommentDto.builder()
                        .content(comment.getContent())
                        .createdDate(comment.getCreatedDate())
                        .id(comment.getId())
                        .postId(comment.getPostId())
                        .username(comment.getUsername())
                        .build()).toList())
                .createdDate(post.getCreatedDate())
                .description(post.getDescription())
                .id(post.getId())
                .username(post.getUsername())
                .imageId(post.getImageId())
                .like(post.getLike())
                .likeCount(post.getLikeCount())
                .commentCount(post.getCommentCount())
                .image(ImageDto.builder()
                        .id(image.getId())
                        .name(image.getName())
                        .type(image.getContentType())
                        .data(Base64.getEncoder().encodeToString(image.getData()))
                        .build())
                .build();
    }

    public PostDto getPost(String id){
        Post post = postRepository.findById(id).orElseThrow(() -> new GenericException("Post not found"));
        Image image = imageRepository.findById(post.getImageId()).orElseThrow(() -> new GenericException("Image not found"));
        PostDto postDto = PostDto.builder()
                .id(post.getId())
                .username(post.getUsername())
                .description(post.getDescription())
                .createdDate(post.getCreatedDate())
                .imageId(post.getImageId())
                .like(post.getLike())
                .likeCount(post.getLikeCount())
                .commentCount(post.getCommentCount())
                .comments(post
                        .getComments()
                        .stream()
                        .map(comment -> CommentDto.builder()
                        .content(comment.getContent())
                        .createdDate(comment.getCreatedDate())
                        .id(comment.getId())
                        .postId(comment.getPostId())
                        .username(comment.getUsername())
                        .build()).toList())

                .image(ImageDto.builder()
                        .id(image.getId())
                        .name(image.getName())
                        .type(image.getContentType())
                        .data(Base64.getEncoder().encodeToString(image.getData()))
                        .build())
                .build();
        return postDto;
    }

    public void likePost(String postId, String username){
        Post post = postRepository.findById(postId).orElseThrow(() -> new GenericException("Post not found"));
        List<String> likes = post.getLike();
        if(likes.contains(username)){
            likes.remove(username);
        }else{
            likes.add(username);
        }
        post.setLike(likes);
        post.setLikeCount((long) likes.size());
        postRepository.save(post);
    }

    public void commentToPost( CommentDto commentDto){
        Post post = postRepository.findById(commentDto.getPostId()).orElseThrow(() -> new GenericException("Post not found"));
        Comment comment = Comment.builder()
                .username(commentDto.getUsername())
                .postId(commentDto.getPostId())
                .content(commentDto.getContent())
                .createdDate(new Date())
                .build();
        commentRepository.save(comment);

        List<Comment> comments = post.getComments();
        comments.add(comment);
        post.setComments(comments);
        post.setCommentCount((long) comments.size());
        postRepository.save(post);
    }

    public void deleteCommentToPost(String postId,String commentId,String username){
        Post post = postRepository.findById(postId).orElseThrow(() -> new GenericException("Post not found"));
        int count = post.getComments().size();
        post.setComments(post.getComments().stream().filter(comment -> !comment.getId().equals(commentId) && comment.getUsername().equals(username)).toList());
        if(count == post.getComments().size()){
            throw new GenericException("Comment not found");
        }
        post.setCommentCount((long) post.getComments().size());
        postRepository.save(post);
    }

    public List<PostDto> getAllPostsFromUser(String username){
        List<Post> posts = postRepository.findAllByUsername(username);
        List<PostDto> postDtos = new ArrayList<>();
        for (Post post : posts) {
            Image image = imageRepository.findById(post.getImageId()).orElseThrow(() -> new IllegalArgumentException("Image not found"));
            PostDto postDto = PostDto.builder()
                    .id(post.getId())
                    .username(post.getUsername())
                    .description(post.getDescription())
                    .createdDate(post.getCreatedDate())
                    .imageId(post.getImageId())
                    .like(post.getLike())
                    .likeCount(post.getLikeCount())
                    .commentCount(post.getCommentCount())
                    .image(ImageDto.builder()
                            .id(image.getId())
                            .name(image.getName())
                            .type(image.getContentType())
                            .data(Base64.getEncoder().encodeToString(image.getData()))
                            .build())
                    .build();
            postDtos.add(postDto);
        }
        return postDtos;
    }



}
