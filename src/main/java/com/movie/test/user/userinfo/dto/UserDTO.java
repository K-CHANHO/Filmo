package com.movie.test.user.userinfo.dto;

import com.movie.test.common.dto.BaseTimeDTO;
import com.movie.test.user.userinfo.entity.UserEntity;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//@Hidden
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO extends BaseTimeDTO {

    private String uid;
    private String userId; // 유저아이디(고유)
    private String type; // kakao OR google OR naver
    private String nickname; // 별명
    private String profileURL; // 프로필사진 url
    private Timestamp lastLoginDate; // 마지막 로그인 시간
    private String introduction; // 소개글
    private List<String> roles;

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
                .roles(Arrays.stream(entity.getRoles().split(",")).toList())
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
                .roles(dto.getRoles().stream().collect(Collectors.joining(",")))
                .build();

        return entity;
    }
}


