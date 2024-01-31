package com.movie.test.report.bookmark.service;

import com.movie.test.report.bookmark.dto.BookmarkDTO;
import com.movie.test.report.bookmark.entity.BookmarkEntity;
import com.movie.test.report.bookmark.repository.BookmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookmarkServiceImpl implements BookmarkService{

    private final BookmarkRepository bookmarkRepository;

    @Override
    public BookmarkDTO registBookmark(BookmarkDTO bookmarkDTO) {

        BookmarkEntity savedBookmark = bookmarkRepository.save(BookmarkDTO.toEntity(bookmarkDTO));

        return BookmarkDTO.toDTO(savedBookmark);
    }

    @Override
    public void deleteBookmark(Long bookmarkId) {
        bookmarkRepository.deleteById(bookmarkId);
    }
}
