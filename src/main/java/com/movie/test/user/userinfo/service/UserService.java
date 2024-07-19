package com.movie.test.user.userinfo.service;

import com.movie.test.user.token.dto.JwtTokenDTO;
import com.movie.test.user.userinfo.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto newUserSave(UserDto userDTO);

    String makeNickname();

    UserDto getUserInfo(String userid);
    UserDto getUserInfoByUidAndType(String uid, String type);

    JwtTokenDTO login(UserDto userDTO);

    List<String> checkUserRoles(String userId);

    UserDto updateUserinfo(UserDto userDTO);
}
