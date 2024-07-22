package com.movie.test.user.userinfo.service;

import com.movie.test.user.token.dto.JwtTokenDTO;
import com.movie.test.user.userinfo.dto.UserDto;
import com.movie.test.user.userinfo.dto.UserInfoModifyDto;
import com.movie.test.user.userinfo.dto.UserSignupDto;

import java.util.List;

public interface UserService {

    UserDto userSignup(UserSignupDto userSignupDto);

    UserDto getUserInfo(String userid);
    UserDto getUserInfoByUidAndType(String uid, String type);

    JwtTokenDTO login(UserDto userDTO);

    List<String> checkUserRoles(String userId);

    UserDto updateUserinfo(UserInfoModifyDto userInfoModifyDto, String loginId);

    boolean isExistUser(String uid, String type);
}
