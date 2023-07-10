package com.socialnetwork.main.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "auth",url = "http://auth:8181/api/v1/login/")
public interface AuthClient {
    @GetMapping("/getroles")
    List<String> getAuthorities(@RequestParam String username);

}