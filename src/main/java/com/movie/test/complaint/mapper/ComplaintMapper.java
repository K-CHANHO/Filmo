package com.movie.test.complaint.mapper;

import com.movie.test.complaint.dto.ComplaintDto;
import com.movie.test.complaint.entity.ComplaintEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ComplaintMapper {
    ComplaintMapper INSTANCE = Mappers.getMapper(ComplaintMapper.class);

    ComplaintEntity toEntity(ComplaintDto dto);
    ComplaintDto toDto(ComplaintEntity entity);

}
