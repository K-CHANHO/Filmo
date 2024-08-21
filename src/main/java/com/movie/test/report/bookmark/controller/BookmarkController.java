package com.movie.test.report.bookmark.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.movie.test.report.bookmark.dto.BookmarkDTO;
import com.movie.test.report.bookmark.dto.BookmarkListDto;
import com.movie.test.report.bookmark.service.BookmarkService;
import com.movie.test.report.report.service.ReportService;
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
    private final ReportService reportService;
    private final Gson gson;

    @Operation(summary = "북마크 리스트 조회", description = "북마크한 감상문을 조회합니다.")
    @Parameters(value = {
            @Parameter(name = "bookmarkId", description = "마지막으로 조회된 북마크 아이디, 최초는 빈값"),
    })
    @GetMapping("/list")
    public ResponseEntity getBookmarkList(BookmarkListDto bookmarkListDto, @Parameter(hidden = true) Pageable pageable) {

        Slice<BookmarkDTO> bookmarkList = bookmarkService.getBookmarkList(bookmarkListDto, pageable);

        JsonObject returnData = new JsonObject();
        returnData.addProperty("bookmarkList", gson.toJson(bookmarkList.getContent()));
        returnData.addProperty("hasNext", bookmarkList.hasNext());

        return new ResponseEntity(returnData, HttpStatus.OK);
    }

    @Operation(summary = "북마크 등록", description = "감상문을 북마크합니다.")
    @Parameters(value = {
            @Parameter(name = "reportId", description = "북마크하려는 감상문 아이디", required = true),
    })
    @PostMapping("/regist")
    public ResponseEntity registBookmark(BookmarkDTO bookmarkDTO) {

        boolean validationReportId = reportService.validationReportId(bookmarkDTO.getReportId());
        if(!validationReportId) {
            return new ResponseEntity("북마크하려는 게시물이 없습니다. 다시 시도해주세요.", HttpStatus.BAD_REQUEST);
        }

        BookmarkDTO savedBookmark = bookmarkService.registBookmark(bookmarkDTO);

        return new ResponseEntity(savedBookmark, HttpStatus.OK);
    }

    @Operation(summary = "북마크 삭제", description = "북마크한 감상문을 삭제합니다.")
    @Parameters(value = {
            @Parameter(name = "bookmarkId", description = "북마크 아이디", required = true),
    })
    @DeleteMapping("/delete")
    public ResponseEntity deleteBookmark(Long bookmarkId){

        if (!bookmarkService.validationBookmarkId(bookmarkId)) {
            return new ResponseEntity("잘못된 시도입니다. 다시 시도해주세요.", HttpStatus.BAD_REQUEST);
        }
        bookmarkService.deleteBookmark(bookmarkId);

        return new ResponseEntity(HttpStatus.OK);
    }

    @Operation(summary = "북마크 수 조회", description = "해당 게시물이 북마크된 수를 조회합니다.")
    @Parameters(value = {
            @Parameter(name = "reportId", description = "조회하려는 감상문 아이디"),
    })
    @GetMapping("/count")
    public ResponseEntity getBookmarkCount(String reportId) {

        Long bookmarkCount = bookmarkService.getBookmarkCount(reportId);

        Map<String, Object> returnData = new HashMap<>();
        returnData.put("bookmarkCount", bookmarkCount);

        return new ResponseEntity(returnData, HttpStatus.OK);
    }

}
