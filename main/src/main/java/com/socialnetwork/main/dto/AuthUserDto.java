package com.socialnetwork.main.dto;

import com.socialnetwork.main.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthUserDto {
    private Long id;
    private String nameSurname;
    private String username;
    private String password;

    private List<Role> roles;
}
