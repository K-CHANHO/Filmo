package com.movie.test.login.service;

import com.movie.test.user.dto.UserDTO;
import com.movie.test.user.entity.UserEntity;

public interface LoginService {
    UserDTO isExistUser(UserDTO userDTO);

    /**
     * USER의 DTO객체를 ENTITY객체로 변환
     * @param userDTO
     * @return userEntity
     */
    default UserEntity dtoTOentity(UserDTO userDTO){

        UserEntity user = UserEntity.builder()
                .uid(userDTO.getUid())
                .userId(userDTO.getUserId())
                .nickname(userDTO.getNickname())
                .type(userDTO.getType())
                .profileURL(userDTO.getProfileURL())
//                .createDate(userDTO.getCreateDate())
                .lastLoginDate(userDTO.getLastLoginDate())
                .build();

        return user;
    }

    /**
     * USER의 ENTITY객체를 DTO객체로 변환
     * @param userEntity
     * @return userDTO
     */
    default UserDTO entityTOdto(UserEntity userEntity){

        UserDTO user = UserDTO.builder()
                .uid(userEntity.getUid())
                .userId(userEntity.getUserId())
                .nickname(userEntity.getNickname())
                .type(userEntity.getType())
                .profileURL(userEntity.getProfileURL())
                .createDate(userEntity.getCreateDate())
                .lastLoginDate(userEntity.getLastLoginDate())
                .build();

        return user;
    }
}
