package com.movie.test.notification.controller;

import com.movie.test.notification.dto.NotificationDTO;
import com.movie.test.notification.service.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Tag(name = "공지사항", description = "공지사항 관련 API")
@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @Operation(summary = "공지사항 등록", description = "공지사항을 등록합니다.")
    @Parameters(value = {
            @Parameter(name = "title", description = "공지사항 제목", required = true),
            @Parameter(name = "content", description = "공지사항 내용", required = true),
            @Parameter(name = "type", description = "타입", required = true),
    })
    @ApiResponse(responseCode = "200", description = "등록한 공지사항 스키마 리턴")
    @PostMapping("/regist")
    public ResponseEntity registNoti(NotificationDTO notificationDTO) {
        NotificationDTO notiDTO = notificationService.registNoti(notificationDTO);

        return new ResponseEntity(notiDTO, HttpStatus.OK);
    }

    @GetMapping("/getList")
    @Operation(summary = "공지사항 리스트", description = "공지사항 리스트를 조회합니다.")
    @Parameters(value = {
            @Parameter(name = "notificationId", description = "마지막에 조회된 공지사항 Id, 빈 값일 경우 전체조회", required = false),
    })
    @ApiResponse(responseCode = "200", description = "공지사항 리스트 및 다음페이지 존재 유무 리턴")
    public ResponseEntity getNotiList(Long notificationId, @PageableDefault(size = 3) @Parameter(hidden = true) Pageable pageable) {
        Slice<NotificationDTO> notiList = notificationService.getNotiList(notificationId, pageable);
        boolean hasNext = notiList.hasNext();

        Map<String, Object> returnData = new HashMap<>();
        returnData.put("hasNext", hasNext);
        returnData.put("notiList", notiList.getContent());

        return new ResponseEntity(returnData, HttpStatus.OK);
    }

    @Operation(summary = "공지사항 조회", description = "공지사항을 조회합니다.")
    @Parameters(value = {
            @Parameter(name = "notificationId", description = "공지사항 Id"),
    })
    @ApiResponse(responseCode = "200", description = "공지사항 스키마 리턴")
    @GetMapping("/getNoti/{notificationId}")
    public ResponseEntity getNoti(@PathVariable Long notificationId) {
        NotificationDTO noti = notificationService.getNoti(notificationId);

        return new ResponseEntity(noti, HttpStatus.OK);
    }

    @Operation(summary = "공지사항 삭제", description = "공지사항을 삭제합니다.")
    @Parameters(value = {
            @Parameter(name = "notificationId", description = "공지사항 Id"),
    })
    @ApiResponse(responseCode = "200", description = "성공 시 'Notification Deleted' 메세지 리턴")
    @DeleteMapping("/delete/{notificationId}")
    public ResponseEntity deleteNoti(@PathVariable Long notificationId) {
        notificationService.deleteNoti(notificationId);

        return new ResponseEntity("Notification Deleted", HttpStatus.OK);
    }

    @Operation(summary = "공지사항 수정", description = "공지사항을 수정합니다.")
    @Parameters(value = {
            @Parameter(name = "notificationId", description = "공지사항 id", required = true),
            @Parameter(name = "title", description = "공지사항 제목", required = true),
            @Parameter(name = "content", description = "공지사항 내용", required = true),
            @Parameter(name = "type", description = "타입", required = true)
    })
    @ApiResponse(responseCode = "200", description = "공지사항 Id 리턴")
    @PutMapping("/update")
    public ResponseEntity updateNoti(NotificationDTO notificationDTO) {
        Long notificationId = notificationService.modifyNoti(notificationDTO);

        return new ResponseEntity(notificationId, HttpStatus.OK);
    }
}
