package com.movie.test.report.reply.service;

import com.movie.test.report.reply.dto.ReplyDto;
import com.movie.test.report.reply.dto.ReplySaveDto;
import com.movie.test.report.reply.dto.ReplyUpdateDto;

import java.util.List;

public interface ReplyService {

    ReplySaveDto saveReply(ReplySaveDto replySaveDtoe);
    ReplyDto updateReply(ReplyUpdateDto replyUpdateDto);

    List<ReplyDto> getReplies(String reportId);
    List<ReplyDto> getSubReplies(String replyId);

    void deleteReply(String replyId);

    void deleteRepliesByReportId(String reportId);
}
