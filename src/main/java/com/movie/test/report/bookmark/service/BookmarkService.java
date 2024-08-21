package com.movie.test.report.bookmark.service;

import com.movie.test.report.bookmark.dto.BookmarkDTO;
import com.movie.test.report.bookmark.dto.BookmarkListDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface BookmarkService {

    BookmarkDTO registBookmark(BookmarkDTO bookmarkDTO);
    void deleteBookmark(Long bookmarkId);

    Slice<BookmarkDTO> getBookmarkList(BookmarkListDto bookmarkListDto, Pageable pageable);

    boolean validationBookmarkId(Long bookmarkId);

    Long getBookmarkCount(String reportId);

}
