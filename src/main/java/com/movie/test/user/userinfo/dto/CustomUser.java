package com.movie.test.user.userinfo.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CustomUser extends User {

    private final String userId;
    private final String nickname;

    public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities, String userId, String nickname) {
        super(username, password, authorities);
        this.userId = userId;
        this.nickname = nickname;
    }

    public CustomUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, String userId, String nickname, String userId1, String nickname1) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.userId = userId1;
        this.nickname = nickname1;
    }
}
