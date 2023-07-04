package com.socialnetwork.main.client;

import com.socialnetwork.main.dto.AuthUserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "auth",url = "http://auth:8181/api/v1/login/")
public interface AuthClient {
    @PostMapping("/save")
    ResponseEntity<String> save(@RequestBody AuthUserDto authUserDto);


    @PostMapping("/auth")
    ResponseEntity<String> auth(@RequestBody AuthUserDto authUserDto);

    @GetMapping("/getUser")
    ResponseEntity<AuthUserDto> getUserByToken(@RequestParam String token);

}