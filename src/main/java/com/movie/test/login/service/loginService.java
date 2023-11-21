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
                .create_date(userDTO.getCreate_date())
                .last_login_date(userDTO.getLast_login_date())
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
                .create_date(userEntity.getCreate_date())
                .last_login_date(userEntity.getLast_login_date())
                .build();

        return user;
    }
}
