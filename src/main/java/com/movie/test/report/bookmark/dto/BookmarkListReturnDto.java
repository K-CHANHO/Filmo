package com.movie.test.report.bookmark.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookmarkListReturnDto {

    private List<BookmarkDto> bookmarkList;
    private boolean hasNext;
}
