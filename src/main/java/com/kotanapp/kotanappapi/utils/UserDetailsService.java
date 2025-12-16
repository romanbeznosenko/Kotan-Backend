package com.kotanapp.kotanappapi.utils;

import com.kotanapp.kotanappapi.core.user.models.UserDAO;
import com.kotanapp.kotanappapi.utils.enums.UserTypeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    public CustomUserDetails loadUserAndAuthority(UserDAO user, List<String> permission) {

        return CustomUserDetails.builder()
                                .user(user)
                                .email(user.getEmail())
                                .authorities(userAuthorities(permission, user))
                                .build();
    }

    private Collection<? extends GrantedAuthority> userAuthorities(List<String> permissions,
                                                                   UserDAO user) {

        HashSet<GrantedAuthority> authorities = new HashSet<>();

        //Role based authorities
        authorities.add(new SimpleGrantedAuthority("USER"));

        if (user != null && user.getUserType().equals(UserTypeEnum.ADMIN)) {
            authorities.add(new SimpleGrantedAuthority("ADMIN"));
        }

        //Permissions
        if (permissions != null) {
            permissions.forEach(permission -> authorities.add(new SimpleGrantedAuthority(permission)));
        }
        return authorities;
    }


    //Default
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

}
