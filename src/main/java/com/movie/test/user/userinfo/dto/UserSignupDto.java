package com.movie.test.user.userinfo.dto;

import com.movie.test.user.userinfo.mapper.UserSignupMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserSignupDto {

    private String email;
    private String type;
    private String profileUrl;

    public static UserDto toUserDto(UserSignupDto userSignupDto){
        return UserSignupMapper.INSTANCE.toUserDto(userSignupDto);
    }

    public static UserSignupDto toUserSignupDto(UserDto userDto){
        return UserSignupMapper.INSTANCE.toUserSignupDto(userDto);
    }

}
