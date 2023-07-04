package com.socialnetwork.main.service;

import com.socialnetwork.main.client.AuthClient;
import com.socialnetwork.main.dto.AuthUserDto;
import com.socialnetwork.main.entity.AuthUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final AuthClient authClient;

    @Override
    public UserDetails loadUserByUsername(String token) throws UsernameNotFoundException {

        AuthUserDto authUserDto = authClient.getUserByToken(token).getBody();
        assert authUserDto != null;
        return AuthUser.builder()
                .username(authUserDto.getUsername())
                .password(authUserDto.getPassword())
                .roles(authUserDto.getRoles())
                .nameSurname(authUserDto.getNameSurname())
                .build();
    }

}
