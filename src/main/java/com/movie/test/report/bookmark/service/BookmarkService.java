package com.movie.test.report.bookmark.service;

import com.movie.test.report.bookmark.dto.BookmarkDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface BookmarkService {

    BookmarkDTO registBookmark(BookmarkDTO bookmarkDTO);
    void deleteBookmark(Long bookmarkId);

    Slice<BookmarkDTO> getBookmarkList(BookmarkDTO bookmarkDTO, Pageable pageable);

}
