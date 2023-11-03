package com.movie.test.user.service;

import com.movie.test.user.dto.userDTO;
import com.movie.test.user.entity.userEntity;

public interface userService {

    public userDTO userSave(userDTO userDTO);

    default userEntity dtoTOentity(userDTO userDTO) {
        userEntity entity = userEntity.builder()
                .uid(userDTO.getUid())
                .userid(userDTO.getUserid())
                .type(userDTO.getType())
                .nickname(userDTO.getNickname())
                .create_date(userDTO.getCreate_date())
                .last_login_date(userDTO.getLast_login_date())
                .profileURL(userDTO.getProfileURL())
                .build();

        return entity;
    }

    default userDTO entityTOdto(userEntity userEntity) {

        userDTO dto = userDTO.builder()
                .uid(userEntity.getUid())
                .userid(userEntity.getUserid())
                .type(userEntity.getType())
                .nickname(userEntity.getNickname())
                .create_date(userEntity.getCreate_date())
                .last_login_date(userEntity.getLast_login_date())
                .profileURL(userEntity.getProfileURL())
                .build();

        return dto;
    }
}
