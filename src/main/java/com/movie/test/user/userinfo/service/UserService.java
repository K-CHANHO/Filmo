package com.movie.test.user.userinfo.service;

import com.movie.test.user.userinfo.dto.UserDTO;

public interface UserService {

    UserDTO newUserSave(UserDTO userDTO);

    String makeNickname();

    UserDTO getUserInfo(String userid);
    UserDTO getUserInfoByUidAndType(String uid, String type);

}
