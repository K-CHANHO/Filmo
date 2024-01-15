package com.movie.test.reply.controller;

import com.movie.test.reply.dto.ReplyDTO;
import com.movie.test.reply.service.ReplyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "댓글", description = "댓글 관련 API 입니다.")
@RestController
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    @Operation(summary = "댓글 전체 가져오기", description = "해당 감상문의 댓글을 전부 가져옵니다")
    @Parameter(name = "reportId", description = "감상문 id", required = true)
    @GetMapping("/getReplies/{reportId}")
    public List<ReplyDTO> getReplies(@PathVariable String reportId) {
        List<ReplyDTO> replies = replyService.getReplies(reportId);

        return replies;
    }

    @Operation(summary = "댓글 등록", description = "감상문에 댓글 등록합니다.")
    @Parameters({
        @Parameter(name = "reportId", description = "감상문 id", required = true),
        @Parameter(name = "userId", description = "유저 id", required = true),
        @Parameter(name = "content", description = "댓글 내용", required = true),
        @Parameter(name = "upReplyId", description = "대댓글일 경우 원댓글 id"),
    })
    @PostMapping("/registReply")
    public void registReply(ReplyDTO replyDTO) {

        replyService.registReply(replyDTO);
    }

    @Operation(summary = "댓글 수정", description = "댓글을 수정합니다.")
    @Parameters(value = {
            @Parameter(name = "replyId", description = "수정할 댓글 id", required = true),
            @Parameter(name = "content", description = "수정할 댓글 내용", required = true),
    })
    @PostMapping("/modifyReply")
    public void modifyReply(ReplyDTO replyDTO) {
        replyService.modifyReply(replyDTO);
    }

    @Operation(summary = "댓글 삭제", description = "댓글을 삭제합니다.")
    @Parameter(name = "replyId", description = "삭제할 댓글 id", required = true)
    @GetMapping("/deleteReply/{replyId}")
    public void deleteReply(@PathVariable String replyId){
        replyService.deleteReply(replyId);
    }
}
