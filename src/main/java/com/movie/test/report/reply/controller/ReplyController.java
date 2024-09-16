package com.movie.test.report.reply.controller;

import com.movie.test.report.reply.dto.ReplyDto;
import com.movie.test.report.reply.dto.ReplySaveDto;
import com.movie.test.report.reply.service.ReplyService;
import com.movie.test.user.userinfo.dto.CustomUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "댓글", description = "댓글 관련 API 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/reply")
public class ReplyController {

    private final ReplyService replyService;

    @Operation(summary = "댓글 가져오기", description = "해당 감상문의 댓글을 조회합니다")
    @Parameter(name = "reportId", description = "감상문 id", required = true)
    @GetMapping("/getReplies/{reportId}")
    public List<ReplyDto> getReplies(@PathVariable String reportId) {
        List<ReplyDto> replies = replyService.getReplies(reportId);

        return replies;
    }

    @Operation(summary = "서브댓글 가져오기", description = "원댓글의 서브댓글을 조회합니다", deprecated = true)
    @Parameter(name = "replyId", description = "원댓글 id", required = true)
    @GetMapping("/getSubReplies/{replyId}")
    public List<ReplyDto> getSubReplies(@PathVariable String replyId) {
        List<ReplyDto> replies = replyService.getSubReplies(replyId);

        return replies;
    }

    @Operation(summary = "댓글 등록", description = "감상문에 댓글을 등록합니다.")
    @PostMapping("/save")
    public ResponseEntity saveReply(@RequestBody ReplySaveDto replySaveDto, @AuthenticationPrincipal CustomUser user) {

        replySaveDto.setUserId(user.getUserId());
        ReplySaveDto savedReply = replyService.saveReply(replySaveDto);

        return new ResponseEntity(savedReply, HttpStatus.OK);
    }

    @Operation(summary = "댓글 수정", description = "댓글을 수정합니다.")

    @PatchMapping("/update")
    public void modifyReply(@RequestBody ReplyDto replyDTO) {
        replyService.modifyReply(replyDTO);
    }

    @Operation(summary = "댓글 삭제", description = "댓글을 삭제합니다.")
    @Parameter(name = "replyId", description = "삭제할 댓글 id", required = true)
    @GetMapping("/deleteReply/{replyId}")
    public void deleteReply(@PathVariable String replyId) {
        replyService.deleteReply(replyId);
    }
}
