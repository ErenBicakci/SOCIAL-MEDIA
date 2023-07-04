package com.socialnetwork.auth.dto;

import com.socialnetwork.auth.entity.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@Builder
public class UserDto {
    private Long id;
    private String nameSurname;
    private String username;
    private String password;

    private List<Role> roles;

    public String toJSON(){
        return "{" +
                "\"id\":\"" + id + "\""+
                ", \"nameSurname\":\"" + nameSurname + "\"" +
                ", \"username\":\"" + username + "\"" +
                ", \"password\":\"" + password + "\"" +
                ", \"roles\":\"" + roles  + "\""+
                "}";
    }

}
