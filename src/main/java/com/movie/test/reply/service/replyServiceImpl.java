package com.movie.test.reply.service;

import com.movie.test.reply.dto.replyDTO;
import com.movie.test.reply.entity.replyEntity;
import com.movie.test.reply.repository.replyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class replyServiceImpl implements replyService{

    @Autowired
    private replyRepository replyRepository;

    @Override
    public replyDTO registReply(replyDTO replyDTO) {

        replyEntity reply = replyEntity.builder()
                .replyId(UUID.randomUUID().toString())
                .upReplyId(replyDTO.getUpReplyId())
                .userId(replyDTO.getUserId())
                .reportId(replyDTO.getUserId())
                .content(replyDTO.getContent())
                .build();

        replyDTO savedReply = entityTOdto(replyRepository.save(reply));

        return savedReply;
    }

    @Override
    public replyDTO modifyReply(replyDTO replyDTO) {

        replyEntity reply = replyEntity.builder()
                .replyId(replyDTO.getReplyId())
                .upReplyId(replyDTO.getUpReplyId())
                .userId(replyDTO.getUserId())
                .reportId(replyDTO.getUserId())
                .content(replyDTO.getContent())
                .build();

        replyDTO savedReply = entityTOdto(replyRepository.save(reply));

        return savedReply;
    }

    @Override
    public List<replyDTO> getReplies(String reportId) {
        List<replyDTO> replyDTOS = new ArrayList<>();

        List<replyEntity> replies = replyRepository.findByReportId(reportId);
        replies.forEach((reply)->{
            replyDTOS.add(entityTOdto(reply));
        });

        return replyDTOS;
    }

    @Override
    public void deleteReply(String replyId) {
        replyRepository.deleteById(replyId);
    }
}
