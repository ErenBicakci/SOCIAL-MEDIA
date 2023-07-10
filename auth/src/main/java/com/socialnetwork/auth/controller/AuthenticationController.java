package com.socialnetwork.auth.controller;


import com.socialnetwork.auth.dto.UserDto;
import com.socialnetwork.auth.log.CustomLogInfo;
import com.socialnetwork.auth.service.AuthenticationService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api/v1/login")
public class AuthenticationController {
     private final AuthenticationService authenticationService;
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @CustomLogInfo
    @PostMapping("/save")
    public ResponseEntity<String> save (@RequestBody UserDto userDto){
        String responseObject = authenticationService.save(userDto);
        return ResponseEntity.ok(responseObject);
    }


    @CustomLogInfo
    @PostMapping("/auth")
    public ResponseEntity<String> auth(@RequestBody UserDto userDto){
        String responseObject = authenticationService.auth(userDto);
        return ResponseEntity.ok(responseObject);
    }

    @CustomLogInfo
    @GetMapping("/getroles")
    public List<String> getRoles(@RequestParam String username){
        return authenticationService.getUserRoles(username);
    }
}
