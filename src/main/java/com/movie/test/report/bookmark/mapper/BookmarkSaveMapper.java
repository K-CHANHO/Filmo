package com.movie.test.report.bookmark.mapper;

import com.movie.test.report.bookmark.dto.BookmarkDto;
import com.movie.test.report.bookmark.dto.BookmarkSaveDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookmarkSaveMapper {
    BookmarkSaveMapper INSTANCE = Mappers.getMapper(BookmarkSaveMapper.class);

    BookmarkDto toBookmark(BookmarkSaveDto saveDto);

}
