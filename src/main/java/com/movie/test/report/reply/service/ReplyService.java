package com.movie.test.report.reply.service;

import com.movie.test.report.reply.dto.ReplyDto;
import com.movie.test.report.reply.dto.ReplySaveDto;

import java.util.List;

public interface ReplyService {

    ReplySaveDto saveReply(ReplySaveDto replySaveDtoe);
    ReplyDto modifyReply(ReplyDto replyDTO);

    List<ReplyDto> getReplies(String reportId);
    List<ReplyDto> getSubReplies(String replyId);

    void deleteReply(String replyId);

    void deleteRepliesByReportId(String reportId);
}
