package com.socialnetwork.main.controller;

import com.socialnetwork.main.dto.ProfileDto;
import com.socialnetwork.main.dto.UserDto;
import com.socialnetwork.main.log.CustomLogInfo;
import com.socialnetwork.main.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @CustomLogInfo
    @GetMapping
    public UserDto getUser(@RequestParam String username){
        return userService.getUserByUsername(username);
    }

    @CustomLogInfo
    @GetMapping("/profile")
    public ProfileDto getProfile(@RequestParam String username){
        return userService.getUserProfileByUsername(username);
    }

    @CustomLogInfo
    @GetMapping("/profile/followers")
    public List<String> getProfileFollowers(@RequestParam String username){
        return userService.getUserFollowersByUsername(username);
    }

    @CustomLogInfo
    @GetMapping("/profile/following")
    public List<String> getProfileFollowing(@RequestParam String username){
        return userService.getUserFollowingByUsername(username);
    }

    @CustomLogInfo
    @GetMapping("/profile/follow")
    public void followUser(@RequestParam String username){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        userService.followUser(auth.getName(),username);
    }

    @CustomLogInfo
    @PostMapping("/profile/unfollow")
    public void unFollowUser(@RequestParam String username){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        userService.unFollowUser(auth.getName(),username);
    }

    @CustomLogInfo
    @GetMapping("/isfollowingcontrol")
    public boolean isFollowingControl(@RequestParam String username){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        return userService.isFollowControl(auth.getName(),username);
    }

}
