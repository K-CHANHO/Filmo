package com.movie.test.user.userinfo.mapper;

import com.movie.test.user.userinfo.dto.UserDto;
import com.movie.test.user.userinfo.entity.UserEntity;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-16T12:19:45+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Oracle Corporation)"
)
public class UserMapperImpl implements UserMapper {

    @Override
    public UserEntity toEntity(UserDto userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        UserEntity.UserEntityBuilder<?, ?> userEntity = UserEntity.builder();

        userEntity.createDate( userDTO.getCreateDate() );
        userEntity.lastModifiedDate( userDTO.getLastModifiedDate() );
        userEntity.userId( userDTO.getUserId() );
        userEntity.uid( userDTO.getUid() );
        userEntity.type( userDTO.getType() );
        userEntity.nickname( userDTO.getNickname() );
        userEntity.profileUrl( userDTO.getProfileUrl() );
        userEntity.lastLoginDate( userDTO.getLastLoginDate() );
        userEntity.introduction( userDTO.getIntroduction() );
        userEntity.roles( userDTO.getRoles() );

        return userEntity.build();
    }

    @Override
    public UserDto toDto(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        UserDto.UserDtoBuilder<?, ?> userDto = UserDto.builder();

        userDto.createDate( userEntity.getCreateDate() );
        userDto.lastModifiedDate( userEntity.getLastModifiedDate() );
        userDto.uid( userEntity.getUid() );
        userDto.userId( userEntity.getUserId() );
        userDto.type( userEntity.getType() );
        userDto.nickname( userEntity.getNickname() );
        userDto.profileUrl( userEntity.getProfileUrl() );
        userDto.lastLoginDate( userEntity.getLastLoginDate() );
        userDto.introduction( userEntity.getIntroduction() );
        userDto.roles( userEntity.getRoles() );

        return userDto.build();
    }
}
