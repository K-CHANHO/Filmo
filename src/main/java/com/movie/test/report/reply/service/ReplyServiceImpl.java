package com.movie.test.report.reply.service;

import com.movie.test.report.reply.dto.ReplyDTO;
import com.movie.test.report.reply.entity.ReplyEntity;
import com.movie.test.report.reply.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {

    private final ReplyRepository replyRepository;

    @Override
    public ReplyDTO registReply(ReplyDTO replyDTO) {

        replyDTO.setReplyId(UUID.randomUUID().toString());
        ReplyEntity reply = ReplyDTO.toEntity(replyDTO);

        ReplyDTO savedReply = ReplyDTO.toDTO(replyRepository.save(reply));

        return savedReply;
    }

    @Override
    public ReplyDTO modifyReply(ReplyDTO replyDTO) {
        ReplyEntity originReply = replyRepository.findById(replyDTO.getReplyId()).get();

        ReplyEntity modifiedReply = ReplyEntity.builder()
                .replyId(originReply.getReplyId())
                .upReplyId(originReply.getUpReplyId())
                .userId(originReply.getUserId())
                .reportId(originReply.getReportId())
                .content(replyDTO.getContent())
                .build();

        ReplyDTO savedReply = ReplyDTO.toDTO(replyRepository.save(modifiedReply));

        return savedReply;
    }

    @Override
    public List<ReplyDTO> getReplies(String reportId) {
        List<ReplyDTO> replyDTOS = new ArrayList<>();

        List<ReplyEntity> replies = replyRepository.findByReportIdOrderByCreateDate(reportId);
        replies.forEach((reply)->{
            replyDTOS.add(ReplyDTO.toDTO(reply));
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
