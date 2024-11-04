package com.movie.test.complaint.mapper;

import com.movie.test.complaint.dto.ComplaintDto;
import com.movie.test.complaint.dto.ComplaintSaveDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ComplaintSaveMapper {
    ComplaintSaveMapper INSTANCE = Mappers.getMapper(ComplaintSaveMapper.class);

    ComplaintDto toMetaDto(ComplaintSaveDto dto);
}
