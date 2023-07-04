package com.socialnetwork.main.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Data
@Builder
@Document
public class User {

    @Id
    private String id;
    private String username;
    private String nameSurname;

    private List<String> following;
    private int followingCount;

    private List<String> followers;
    private int followerCount;

    private String imageId;


}
