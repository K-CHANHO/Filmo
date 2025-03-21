package com.movie.test.report.like.mapper;

import com.movie.test.report.like.dto.LikeDto;
import com.movie.test.report.like.entity.LikeEntity;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-04T15:10:03+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Oracle Corporation)"
)
public class LikeMapperImpl implements LikeMapper {

    @Override
    public LikeEntity toEntity(LikeDto dto) {
        if ( dto == null ) {
            return null;
        }

        LikeEntity.LikeEntityBuilder likeEntity = LikeEntity.builder();

        likeEntity.likeId( dto.getLikeId() );
        likeEntity.userId( dto.getUserId() );
        likeEntity.targetId( dto.getTargetId() );
        likeEntity.type( dto.getType() );

        return likeEntity.build();
    }

    @Override
    public LikeDto toDto(LikeEntity entity) {
        if ( entity == null ) {
            return null;
        }

        LikeDto.LikeDtoBuilder likeDto = LikeDto.builder();

        likeDto.likeId( entity.getLikeId() );
        likeDto.userId( entity.getUserId() );
        likeDto.targetId( entity.getTargetId() );
        likeDto.type( entity.getType() );

        return likeDto.build();
    }
}
