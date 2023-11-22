package com.movie.test.login.service;

import com.movie.test.user.dto.userDTO;
import com.movie.test.user.entity.userEntity;

public interface loginService {
    userDTO isExistUser(userDTO userDTO);

    /**
     * USER의 DTO객체를 ENTITY객체로 변환
     * @param userDTO
     * @return userEntity
     */
    default userEntity dtoTOentity(userDTO userDTO){

        userEntity user = userEntity.builder()
                .uid(userDTO.getUid())
                .userId(userDTO.getUserId())
                .nickname(userDTO.getNickname())
                .type(userDTO.getType())
                .profileURL(userDTO.getProfileURL())
                .createDate(userDTO.getCreateDate())
                .lastLoginDate(userDTO.getLastLoginDate())
                .build();

        return user;
    }

    /**
     * USER의 ENTITY객체를 DTO객체로 변환
     * @param userEntity
     * @return userDTO
     */
    default userDTO entityTOdto(userEntity userEntity){

        userDTO user = userDTO.builder()
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
