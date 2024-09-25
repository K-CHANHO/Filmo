package com.movie.test.user.follow.mapper;

import com.movie.test.user.follow.dto.FollowSaveDto;
import com.movie.test.user.follow.entity.FollowEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FollowSaveMapper {

    FollowSaveMapper INSTNACE = Mappers.getMapper(FollowSaveMapper.class);

    FollowEntity toFollowEntity(FollowSaveDto followSaveDto);
    FollowSaveDto toFollowSaveDto(FollowEntity followEntity);
}
