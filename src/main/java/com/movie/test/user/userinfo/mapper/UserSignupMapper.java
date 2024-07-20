package com.movie.test.user.userinfo.mapper;

import com.movie.test.user.userinfo.dto.UserDto;
import com.movie.test.user.userinfo.dto.UserSignupDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserSignupMapper {

    UserSignupMapper INSTANCE = Mappers.getMapper(UserSignupMapper.class);

    UserDto toUserDto(UserSignupDto userSignupDto);
    UserSignupDto toUserSignupDto(UserDto userDto);

}
