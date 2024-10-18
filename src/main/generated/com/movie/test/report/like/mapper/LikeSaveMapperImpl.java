package com.movie.test.report.like.mapper;

import com.movie.test.report.like.dto.LikeDto;
import com.movie.test.report.like.dto.LikeSaveDto;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-18T19:52:51+0900",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.5.jar, environment: Java 17.0.1 (Oracle Corporation)"
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
