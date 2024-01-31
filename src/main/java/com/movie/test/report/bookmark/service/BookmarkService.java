package com.movie.test.report.bookmark.service;

import com.movie.test.report.bookmark.dto.BookmarkDTO;

public interface BookmarkService {

    BookmarkDTO registBookmark(BookmarkDTO bookmarkDTO);
    void deleteBookmark(Long bookmarkId);

}
