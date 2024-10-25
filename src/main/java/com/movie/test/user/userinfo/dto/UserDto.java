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

@Schema(description = "유저정보")
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserDto extends BaseTimeDTO {

    @Schema(description = "소셜로그인 email")
    private String email;

    @Schema(description = "유저 고유값")
    private String userId;

    @Schema(description = "소셜로그인 종류")
    private String type;

    @Schema(description = "닉네임")
    private String nickname;

    @Schema(description = "프로필사진 URL")
    private String profileUrl;

    @Schema(description = "마지막 로그인 시간")
    private Timestamp lastLoginDate;

    @Schema(description = "소개글")
    private String introduction;

    @Schema(description = "권한")
    private String roles;

    public static UserDto toDTO(UserEntity entity) {
        return UserMapper.INSTANCE.toDto(entity);
    }

    public static UserEntity toEntity(UserDto dto) {
        return UserMapper.INSTANCE.toEntity(dto);
    }
}


