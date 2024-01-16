package com.movie.test.report.reply.service;

import com.movie.test.report.reply.dto.ReplyDTO;

import java.util.List;

public interface ReplyService {

    ReplyDTO registReply(ReplyDTO replyDTO);
    ReplyDTO modifyReply(ReplyDTO replyDTO);

    List<ReplyDTO> getReplies(String reportId);
    void deleteReply(String replyId);

    void deleteRepliesByReportId(String reportId);

}
