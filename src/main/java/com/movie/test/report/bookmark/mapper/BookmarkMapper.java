package com.movie.test.report.bookmark.mapper;

import com.movie.test.report.bookmark.dto.BookmarkDto;
import com.movie.test.report.bookmark.entity.BookmarkEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookmarkMapper {

    BookmarkMapper INSTANCE = Mappers.getMapper(BookmarkMapper.class);

    BookmarkEntity toEntity(BookmarkDto dto);
    BookmarkDto toDto(BookmarkEntity entity);
}
