package com.socialnetwork.main.service;

import com.socialnetwork.main.dto.ImageDto;
import com.socialnetwork.main.dto.KMessage;
import com.socialnetwork.main.dto.ProfileDto;
import com.socialnetwork.main.dto.UserDto;
import com.socialnetwork.main.entity.Image;
import com.socialnetwork.main.entity.User;
import com.socialnetwork.main.exception.GenericException;
import com.socialnetwork.main.log.CustomLogDebug;
import com.socialnetwork.main.repository.ImageRepository;
import com.socialnetwork.main.repository.UserRepository;
import org.json.JSONObject;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ImageRepository imageRepository;

    private final PostService postService;

    public UserService(UserRepository userRepository, ImageRepository imageRepository, PostService postService) {
        this.userRepository = userRepository;
        this.imageRepository = imageRepository;
        this.postService = postService;
    }

    @KafkaListener(
            topics = "created-user-topic",
            groupId = "${haydikodlayalim.kafka.group.id}"
    )
    public void listenUserCreate(@Payload KMessage message) {
        System.out.println(message.getMessage());
        JSONObject jsonObject = new JSONObject(message.getMessage());
        User user = User.builder()
                .followers(new ArrayList<>())
                .following(new ArrayList<>())
                .nameSurname(jsonObject.getString("nameSurname"))
                .username(jsonObject.getString("username"))
                .imageId("1")
                .followerCount(0)
                .followingCount(0)
                .build();
        userRepository.save(user);
    }

    @CustomLogDebug
    public UserDto getUserByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new GenericException("User not found"));
        Image image = imageRepository.findById(user.getImageId()).orElseThrow(() -> new GenericException("Image not found"));
        return UserDto.builder()
                .username(user.getUsername())
                .nameSurname(user.getNameSurname())
                .image(ImageDto.builder()
                        .type(image.getContentType())
                        .name(image.getName())
                        .data(Base64.getEncoder().encodeToString(image.getData()))
                        .build())
                .followerCount(user.getFollowerCount())
                .followingCount(user.getFollowingCount())
                .build();
    }

    @CustomLogDebug
    public ProfileDto getUserProfileByUsername(String username) {

        return ProfileDto.builder()
                .posts(postService.getAllPostsFromUser(username))
                .user(getUserByUsername(username))
                .build();
    }

    @CustomLogDebug
    public List<String> getUserFollowersByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new GenericException("User not found"));
        return user.getFollowers();
    }

    @CustomLogDebug
    public List<String> getUserFollowingByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new GenericException("User not found"));
        return user.getFollowing();
    }

    @CustomLogDebug
    public void followUser(String username, String usernameToFollow) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new GenericException("User not found"));
        User userToFollow = userRepository.findByUsername(usernameToFollow).orElseThrow(() -> new GenericException("User not found"));

        user.getFollowing().stream().forEach(following -> {
            if (following.equals(usernameToFollow)) {
                throw new GenericException("User already followed");
            }
        });
        userToFollow.getFollowers().stream().forEach(follower -> {
            if (follower.equals(username)) {
                throw new GenericException("User already followed");
            }
        });

        user.getFollowing().add(usernameToFollow);
        user.setFollowerCount(user.getFollowerCount() + 1);
        userToFollow.getFollowers().add(username);
        userToFollow.setFollowingCount(userToFollow.getFollowingCount() + 1);

        userRepository.save(user);
        userRepository.save(userToFollow);
    }

    @CustomLogDebug
    public void unFollowUser(String username, String usernameToUnFollow) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new GenericException("User not found"));
        User userToUnFollow = userRepository.findByUsername(usernameToUnFollow).orElseThrow(() -> new GenericException("User not found"));

        user.getFollowing().stream().forEach(following -> {
            if (following.equals(usernameToUnFollow)) {
                user.getFollowing().remove(usernameToUnFollow);
                user.setFollowerCount(user.getFollowerCount() - 1);
            }
        });
        userToUnFollow.getFollowers().stream().forEach(follower -> {
            if (follower.equals(username)) {
                userToUnFollow.getFollowers().remove(username);
                userToUnFollow.setFollowingCount(userToUnFollow.getFollowingCount() - 1);
            }
        });

        userRepository.save(user);
        userRepository.save(userToUnFollow);
    }

    @CustomLogDebug
    public boolean isFollowControl(String username, String usernameToFollow) {
        return userRepository
                .findByUsername(username)
                .orElseThrow(() -> new GenericException("User not found"))
                .getFollowing()
                .stream()
                .anyMatch(following -> following.equals(usernameToFollow));
    }

}
