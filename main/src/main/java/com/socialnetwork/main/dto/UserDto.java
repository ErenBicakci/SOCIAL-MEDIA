package com.socialnetwork.main.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {


    private String username;
    private String nameSurname;
    private ImageDto image;
    private int followerCount;
    private int followingCount;
}
