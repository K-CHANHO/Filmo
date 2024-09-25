package com.movie.test.user.follow.mapper;

import com.movie.test.user.follow.dto.FollowSaveDto;
import com.movie.test.user.follow.entity.FollowEntity;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-25T22:33:01+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Oracle Corporation)"
)
public class FollowSaveMapperImpl implements FollowSaveMapper {

    @Override
    public FollowEntity toFollowEntity(FollowSaveDto followSaveDto) {
        if ( followSaveDto == null ) {
            return null;
        }

        FollowEntity.FollowEntityBuilder followEntity = FollowEntity.builder();

        followEntity.followId( followSaveDto.getFollowId() );
        followEntity.userId( followSaveDto.getUserId() );
        followEntity.targetId( followSaveDto.getTargetId() );

        return followEntity.build();
    }

    @Override
    public FollowSaveDto toFollowSaveDto(FollowEntity followEntity) {
        if ( followEntity == null ) {
            return null;
        }

        FollowSaveDto.FollowSaveDtoBuilder followSaveDto = FollowSaveDto.builder();

        followSaveDto.followId( followEntity.getFollowId() );
        followSaveDto.targetId( followEntity.getTargetId() );
        followSaveDto.userId( followEntity.getUserId() );

        return followSaveDto.build();
    }
}
