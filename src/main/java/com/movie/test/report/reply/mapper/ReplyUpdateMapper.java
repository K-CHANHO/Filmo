package com.movie.test.report.reply.mapper;

import com.movie.test.report.reply.dto.ReplyUpdateDto;
import com.movie.test.report.reply.entity.ReplyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReplyUpdateMapper {

    ReplyUpdateMapper INSTANCE = Mappers.getMapper(ReplyUpdateMapper.class);

    ReplyEntity toEntity(ReplyUpdateDto dto);
}
