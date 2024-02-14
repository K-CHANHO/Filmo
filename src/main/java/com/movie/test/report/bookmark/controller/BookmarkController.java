package com.movie.test.report.bookmark.controller;

import com.movie.test.report.bookmark.dto.BookmarkDTO;
import com.movie.test.report.bookmark.service.BookmarkService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
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

    @Operation(summary = "북마크 리스트 조회", description = "북마크한 감상문을 조회합니다.")
    @Parameters(value = {
            @Parameter(name = "userId", description = "유저 아이디", required = true),
            @Parameter(name = "bookmarkId", description = "마지막으로 조회된 북마크 아이디, 최초는 빈값"),
    })
    @GetMapping("/list")
    public ResponseEntity getBookmarkList(BookmarkDTO bookmarkDTO, @Parameter(hidden = true) Pageable pageable) {

        Slice<BookmarkDTO> bookmarkList = bookmarkService.getBookmarkList(bookmarkDTO, pageable);

        Map<String, Object> returnData = new HashMap<>();
        returnData.put("bookmarkList", bookmarkList.getContent());
        returnData.put("hasNext", bookmarkList.hasNext());

        return new ResponseEntity(returnData, HttpStatus.OK);
    }

    @Operation(summary = "북마크 등록", description = "감상문을 북마크합니다.")
    @Parameters(value = {
            @Parameter(name = "userId", description = "유저 아이디", required = true),
            @Parameter(name = "reportId", description = "북마크하려는 감상문 아이디", required = true),
    })
    @PostMapping("/regist")
    public ResponseEntity registBookmark(BookmarkDTO bookmarkDTO) {

        BookmarkDTO savedBookmark = bookmarkService.registBookmark(bookmarkDTO);

        return new ResponseEntity(savedBookmark, HttpStatus.OK);
    }

    @Operation(summary = "북마크 삭제", description = "북마크한 감상문을 삭제합니다.")
    @Parameters(value = {
            @Parameter(name = "bookmarkId", description = "북마크 아이디", required = true),
    })
    @DeleteMapping("/delete")
    public ResponseEntity deleteBookmark(Long bookmarkId){

        bookmarkService.deleteBookmark(bookmarkId);

        return new ResponseEntity(HttpStatus.OK);
    }

}
