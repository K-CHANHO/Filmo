package com.movie.test.user.userinfo.service;

import com.movie.test.user.token.dto.JwtTokenDTO;
import com.movie.test.user.userinfo.dto.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO newUserSave(UserDTO userDTO);

    String makeNickname();

    UserDTO getUserInfo(String userid);
    UserDTO getUserInfoByUidAndType(String uid, String type);

    JwtTokenDTO login(UserDTO userDTO);

    List<String> checkUserRoles(String userId);

    UserDTO updateUserinfo(UserDTO userDTO);
}
