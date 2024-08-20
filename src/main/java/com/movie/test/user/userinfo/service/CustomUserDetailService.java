package com.movie.test.user.userinfo.service;

import com.movie.test.user.userinfo.dto.CustomUser;
import com.movie.test.user.userinfo.entity.UserEntity;
import com.movie.test.user.userinfo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        UserDetails user = userRepository.findByUid(username)
//                .map(this::createUserDetails)
//                .orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 유저입니다."));

        UserEntity user = userRepository.findByUid(username).orElseThrow(()-> new UsernameNotFoundException("존재하지 않는 유저입니다."));
        CustomUser customUser = new CustomUser(user.getUid(), user.getType(), createUserDetails(user).getAuthorities(), user.getUserId(), user.getNickname());
        return customUser;
    }

    public UserDetails createUserDetails(UserEntity member){
        return User.builder()
                .username(member.getUid())
                .password(member.getType())
                .roles(member.getRoles().split(";"))
                .build();
    }
}
