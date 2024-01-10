package com.movie.test.user.dto;

import com.movie.test.common.dto.BaseTimeDTO;
import com.movie.test.user.entity.UserEntity;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;

@Hidden
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO extends BaseTimeDTO {

    private String uid;
    private String userId; // 자체 uid
    private String type; // kakao OR google OR naver
    private String nickname;
    private String profileURL; // 프로필사진 url
    private Timestamp lastLoginDate;
    private String introduction; // 소개글

    public static UserDTO toDTO(UserEntity entity) {
        UserDTO dto = UserDTO.builder()
                .uid(entity.getUid())
                .userId(entity.getUserId())
                .type(entity.getType())
                .nickname(entity.getNickname())
                .profileURL(entity.getProfileURL())
                .lastLoginDate(entity.getLastLoginDate())
                .createDate(entity.getCreateDate())
                .lastModifiedDate(entity.getLastModifiedDate())
                .introduction(entity.getIntroduction())
                .build();

        return dto;
    }

    public static UserEntity toEntity(UserDTO dto) {
        UserEntity entity = UserEntity.builder()
                .uid(dto.getUid())
                .userId(dto.getUserId())
                .type(dto.getType())
                .nickname(dto.getNickname())
                .profileURL(dto.profileURL)
                .lastLoginDate(dto.lastLoginDate)
                .introduction(dto.getIntroduction())
                .build();

        return entity;
    }
}


