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


    default ReplyEntity dtoTOentity(ReplyDTO replyDTO){
        ReplyEntity reply = ReplyEntity.builder()
                .replyId(replyDTO.getReportId())
                .upReplyId(replyDTO.getUpReplyId())
                .reportId(replyDTO.getReportId())
                .content(replyDTO.getContent())
                .userId(replyDTO.getUserId())
                .build();

        return reply;
    }

    default ReplyDTO entityTOdto(ReplyEntity replyEntity) {
        ReplyDTO reply = ReplyDTO.builder()
                .replyId(replyEntity.getReplyId())
                .upReplyId(replyEntity.getUpReplyId())
                .reportId(replyEntity.getReportId())
                .content(replyEntity.getContent())
                .userId(replyEntity.getUserId())
                .build();

        return reply;
    }
}
