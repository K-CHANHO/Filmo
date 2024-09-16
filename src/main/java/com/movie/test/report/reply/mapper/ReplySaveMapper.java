package com.movie.test.report.reply.mapper;

import com.movie.test.report.reply.dto.ReplySaveDto;
import com.movie.test.report.reply.entity.ReplyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReplySaveMapper {

    ReplySaveMapper INSTANCE = Mappers.getMapper(ReplySaveMapper.class);

    ReplyEntity toEntity(ReplySaveDto dto);
}
