package com.movie.test.report.like.mapper;

import com.movie.test.report.like.dto.LikeDto;
import com.movie.test.report.like.dto.LikeSaveDto;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
)
public class LikeSaveMapperImpl implements LikeSaveMapper {

    @Override
    public LikeDto toLikeDto(LikeSaveDto likeSaveDto) {
        if ( likeSaveDto == null ) {
            return null;
        }

        LikeDto.LikeDtoBuilder likeDto = LikeDto.builder();

        likeDto.targetId( likeSaveDto.getTargetId() );
        likeDto.type( likeSaveDto.getType() );

        return likeDto.build();
    }
}
