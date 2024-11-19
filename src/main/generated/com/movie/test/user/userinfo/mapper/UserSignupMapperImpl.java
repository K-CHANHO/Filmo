package com.movie.test.user.userinfo.mapper;

import com.movie.test.user.userinfo.dto.UserDto;
import com.movie.test.user.userinfo.dto.UserSignupDto;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-19T23:24:21+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Oracle Corporation)"
)
public class UserSignupMapperImpl implements UserSignupMapper {

    @Override
    public UserDto toUserDto(UserSignupDto userSignupDto) {
        if ( userSignupDto == null ) {
            return null;
        }

        UserDto.UserDtoBuilder<?, ?> userDto = UserDto.builder();

        userDto.email( userSignupDto.getEmail() );
        userDto.type( userSignupDto.getType() );
        userDto.profileUrl( userSignupDto.getProfileUrl() );

        return userDto.build();
    }

    @Override
    public UserSignupDto toUserSignupDto(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        UserSignupDto.UserSignupDtoBuilder userSignupDto = UserSignupDto.builder();

        userSignupDto.email( userDto.getEmail() );
        userSignupDto.type( userDto.getType() );
        userSignupDto.profileUrl( userDto.getProfileUrl() );

        return userSignupDto.build();
    }
}
