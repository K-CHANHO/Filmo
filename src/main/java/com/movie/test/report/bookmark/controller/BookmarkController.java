package com.movie.test.report.bookmark.controller;

import com.movie.test.report.bookmark.dto.BookmarkDTO;
import com.movie.test.report.bookmark.service.BookmarkService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/bookmark")
@Tag(name = "북마크", description = "북마크 관련 API")
@RequiredArgsConstructor
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @GetMapping("/list")
    public ResponseEntity getBookmarkList(BookmarkDTO bookmarkDTO, Pageable pageable) {

        Slice<BookmarkDTO> bookmarkList = bookmarkService.getBookmarkList(bookmarkDTO, pageable);

        Map<String, Object> returnData = new HashMap<>();
        returnData.put("bookmarkList", bookmarkList.getContent());
        returnData.put("hasNext", bookmarkList.hasNext());

        return new ResponseEntity(returnData, HttpStatus.OK);
    }

    @PostMapping("/regist")
    public ResponseEntity registBookmark(BookmarkDTO bookmarkDTO) {

        BookmarkDTO savedBookmark = bookmarkService.registBookmark(bookmarkDTO);

        return new ResponseEntity(savedBookmark, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteBookmark(Long bookmarkId){

        bookmarkService.deleteBookmark(bookmarkId);

        return new ResponseEntity(HttpStatus.OK);
    }

}
