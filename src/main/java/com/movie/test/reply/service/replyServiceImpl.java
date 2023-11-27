package com.movie.test.reply.service;

import com.movie.test.reply.dto.replyDTO;
import com.movie.test.reply.entity.replyEntity;
import com.movie.test.reply.repository.replyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
                .reportId(replyDTO.getReportId())
                .content(replyDTO.getContent())
                .build();

        replyDTO savedReply = entityTOdto(replyRepository.save(reply));

        return savedReply;
    }

    @Override
    public replyDTO modifyReply(replyDTO replyDTO) {
        replyEntity originReply = replyRepository.findById(replyDTO.getReplyId()).get();
        replyEntity modifiedReply = replyEntity.builder()
                .replyId(replyDTO.getReplyId())
                .upReplyId(originReply.getUpReplyId())
                .userId(originReply.getUserId())
                .reportId(originReply.getReportId())
                .content(replyDTO.getContent())
                .build();

        replyDTO savedReply = entityTOdto(replyRepository.save(modifiedReply));

        return savedReply;
    }

    @Override
    public List<replyDTO> getReplies(String reportId) {
        List<replyDTO> replyDTOS = new ArrayList<>();

        List<replyEntity> replies = replyRepository.findByReportIdOrderByCreateDate(reportId);
        replies.forEach((reply)->{
            replyDTOS.add(entityTOdto(reply));
        });

        return replyDTOS;
    }

    @Override
    @Transactional
    public void deleteReply(String replyId) {
        replyRepository.deleteById(replyId); // 해당 댓글 삭제
        replyRepository.deleteByUpReplyId(replyId); // 해당 댓글의 대댓글 삭제. -> TODO. 추후 JPQL 고려해볼 것.
    }

    @Override
    public void deleteRepliesByReportId(String reportId) {
        replyRepository.deleteByReportId(reportId);
    }
}
