package com.movie.test.user.service;

import com.movie.test.user.dto.UserDTO;
import com.movie.test.user.entity.UserEntity;

public interface UserService {

    UserDTO newUserSave(UserDTO userDTO);

    String makeNickname();

    UserDTO getUserInfo(String userid);

    default UserEntity dtoTOentity(UserDTO userDTO) {
        UserEntity entity = UserEntity.builder()
                .uid(userDTO.getUid())
                .userId(userDTO.getUserId())
                .type(userDTO.getType())
                .nickname(userDTO.getNickname())
                .lastLoginDate(userDTO.getLastLoginDate())
                .profileURL(userDTO.getProfileURL())
                .build();

        return entity;
    }

    default UserDTO entityTOdto(UserEntity userEntity) {

        UserDTO dto = UserDTO.builder()
                .uid(userEntity.getUid())
                .userId(userEntity.getUserId())
                .type(userEntity.getType())
                .nickname(userEntity.getNickname())
                .createDate(userEntity.getCreateDate())
                .lastModifiedDate(userEntity.getLastModifiedDate())
                .lastLoginDate(userEntity.getLastLoginDate())
                .profileURL(userEntity.getProfileURL())
                .build();

        return dto;
    }
}
