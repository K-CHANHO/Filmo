package com.movie.test.user.service;

import com.movie.test.user.dto.UserDTO;
import com.movie.test.user.entity.UserEntity;

public interface UserService {

    UserDTO newUserSave(UserDTO userDTO);

    String makeNickname();

    UserDTO getUserInfo(String userid);

}
