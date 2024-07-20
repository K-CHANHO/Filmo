package com.movie.test.user.userinfo.mapper;

import com.movie.test.user.userinfo.dto.UserDto;
import com.movie.test.user.userinfo.dto.UserSignupDto;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-20T15:17:20+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Oracle Corporation)"
)
public class UserSignupMapperImpl implements UserSignupMapper {

    @Override
    public UserDto toUserDto(UserSignupDto userSignupDto) {
        if ( userSignupDto == null ) {
            return null;
        }

        UserDto.UserDtoBuilder<?, ?> userDto = UserDto.builder();

        userDto.uid( userSignupDto.getUid() );
        userDto.type( userSignupDto.getType() );

        return userDto.build();
    }

    @Override
    public UserSignupDto toUserSignupDto(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        UserSignupDto.UserSignupDtoBuilder userSignupDto = UserSignupDto.builder();

        userSignupDto.uid( userDto.getUid() );
        userSignupDto.type( userDto.getType() );

        return userSignupDto.build();
    }
}
