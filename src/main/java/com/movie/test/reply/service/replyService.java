package com.movie.test.reply.service;

import com.movie.test.reply.dto.replyDTO;
import com.movie.test.reply.entity.replyEntity;
import com.movie.test.report.dto.reportDTO;
import com.movie.test.report.entity.reportEntity;

import java.util.List;

public interface replyService {

    replyDTO registReply(replyDTO replyDTO);
    replyDTO modifyReply(replyDTO replyDTO);

    List<replyDTO> getReplies(String reportId);
    void deleteReply(String replyId);


    default replyEntity dtoTOentity(replyDTO replyDTO){
        replyEntity reply = replyEntity.builder()
                .replyId(replyDTO.getReportId())
                .upReplyId(replyDTO.getUpReplyId())
                .reportId(replyDTO.getReportId())
                .content(replyDTO.getContent())
                .userId(replyDTO.getUserId())
                .build();

        return reply;
    }

    default replyDTO entityTOdto(replyEntity replyEntity) {
        replyDTO reply = replyDTO.builder()
                .replyId(replyEntity.getReportId())
                .upReplyId(replyEntity.getUpReplyId())
                .reportId(replyEntity.getReportId())
                .content(replyEntity.getContent())
                .userId(replyEntity.getUserId())
                .build();

        return reply;
    }
}
