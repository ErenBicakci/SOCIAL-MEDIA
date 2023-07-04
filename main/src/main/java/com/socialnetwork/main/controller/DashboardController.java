package com.socialnetwork.main.controller;

import com.socialnetwork.main.entity.Comment;
import com.socialnetwork.main.entity.Image;
import com.socialnetwork.main.entity.Post;
import com.socialnetwork.main.entity.User;
import com.socialnetwork.main.log.CustomLogInfo;
import com.socialnetwork.main.repository.CommentRepository;
import com.socialnetwork.main.repository.ImageRepository;
import com.socialnetwork.main.repository.PostRepository;
import com.socialnetwork.main.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;

@Log4j2
@RestController
@RequestMapping("/api/v1/dashboard")
@RequiredArgsConstructor
public class DashboardController {


}
