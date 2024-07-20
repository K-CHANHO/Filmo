package com.movie.test.user.userinfo.dto;

import com.movie.test.common.dto.BaseTimeDTO;
import com.movie.test.user.userinfo.entity.UserEntity;
import com.movie.test.user.userinfo.mapper.UserMapper;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Schema(description = "유저정보")
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto extends BaseTimeDTO {

    @Schema(description = "소셜로그인 UID")
    private String uid;

    @Schema(description = "유저 고유값")
    private String userId;

    @Schema(description = "소셜로그인 종류")
    private String type;

    @Schema(description = "닉네임")
    private String nickname;

    @Schema(description = "프로필사진 URL")
    private String profileURL;

    @Schema(description = "마지막 로그인 시간")
    private Timestamp lastLoginDate;

    @Schema(description = "소개글")
    private String introduction;

    @Schema(description = "권한")
    private String roles;

    public static UserDto toDTO(UserEntity entity) {
        /*
        UserDto dto = UserDto.builder()
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
        */
        return UserMapper.INSTANCE.toDto(entity);
    }

    public static UserEntity toEntity(UserDto dto) {
        /*
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
        */
        return UserMapper.INSTANCE.toEntity(dto);
    }
}


