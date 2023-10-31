package com.moive.test.user.service;

import com.moive.test.user.dto.userDTO;
import com.moive.test.user.entity.userEntity;

import java.util.UUID;

public interface userService {

    public userDTO userSave(userDTO userDTO);

    default userEntity dtoTOentity(userDTO userDTO) {
        userEntity entity = userEntity.builder()
                .uid(userDTO.getUid())
                .userid(userDTO.getUserid())
                .type(userDTO.getType())
                .nickname(userDTO.getNickname())
                .build();

        return entity;
    }

    default userDTO entityTOdto(userEntity userEntity) {

        userDTO dto = userDTO.builder()
                .uid(userEntity.getUid())
                .userid(userEntity.getUserid())
                .type(userEntity.getType())
                .nickname(userEntity.getNickname())
                .build();

        return dto;
    }
}
