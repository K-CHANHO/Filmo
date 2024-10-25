package com.movie.test.report.bookmark.dto;

import com.movie.test.report.bookmark.entity.BookmarkEntity;
import com.movie.test.report.bookmark.mapper.BookmarkMapper;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Hidden
public class BookmarkDto {

    private String bookmarkId;
    private String userId;
    private String reportId;

    public static BookmarkEntity toEntity(BookmarkDto dto) {
        return BookmarkMapper.INSTANCE.toEntity(dto);

    }

    public static BookmarkDto toDTO(BookmarkEntity entity){
        return BookmarkMapper.INSTANCE.toDto(entity);
    }

}
