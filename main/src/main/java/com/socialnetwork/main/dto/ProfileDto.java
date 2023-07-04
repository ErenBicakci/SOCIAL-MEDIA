package com.socialnetwork.main.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;


@Builder
@Data
public class ProfileDto {

    private UserDto user;

    private List<PostDto> posts;
}
