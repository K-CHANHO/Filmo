package com.movie.test.reply.service;

import com.movie.test.reply.dto.ReplyDTO;
import com.movie.test.reply.entity.ReplyEntity;

import java.util.List;

public interface ReplyService {

    ReplyDTO registReply(ReplyDTO replyDTO);
    ReplyDTO modifyReply(ReplyDTO replyDTO);

    List<ReplyDTO> getReplies(String reportId);
    void deleteReply(String replyId);

    void deleteRepliesByReportId(String reportId);

}
