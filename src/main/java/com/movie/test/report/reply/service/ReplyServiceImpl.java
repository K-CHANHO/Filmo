package com.movie.test.report.reply.service;

import com.movie.test.common.cef.CustomUUID;
import com.movie.test.report.reply.dto.ReplyDto;
import com.movie.test.report.reply.dto.ReplySaveDto;
import com.movie.test.report.reply.dto.ReplyUpdateDto;
import com.movie.test.report.reply.entity.ReplyEntity;
import com.movie.test.report.reply.repository.ReplyRepository;
import com.movie.test.user.userinfo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {

    private final ReplyRepository replyRepository;
    private final UserRepository userRepository;

    @Override
    public ReplySaveDto saveReply(ReplySaveDto replySaveDto) {

        replySaveDto.setReplyId(CustomUUID.createUUID());
        ReplyEntity replyEntity = ReplySaveDto.toEntity(replySaveDto);
        replyRepository.save(replyEntity);

        return replySaveDto;
    }

    @Override
    public ReplyDto updateReply(ReplyUpdateDto replyUpdateDto) {
        ReplyEntity originReply = replyRepository.findById(replyUpdateDto.getReplyId())
                .orElseThrow();

        ReplyEntity modifiedReply = originReply.toBuilder()
                .content(replyUpdateDto.getContent())
                .build();

        ReplyEntity updatedReply = replyRepository.save(modifiedReply);

        return ReplyDto.toDTO(updatedReply);
    }

    @Override
    public List<ReplyDto> getReplies(String reportId, String userId) {
        List<ReplyDto> replyDtos = new ArrayList<>();

        List<ReplyEntity> replies = replyRepository.findFirstDepthReply(reportId, userId);
            replies.forEach((reply) -> {
                ReplyDto dto = ReplyDto.toDTO(reply);
                dto.setSubReply(getSubReplies(dto.getReplyId(), userId));
                dto.setNickname(userRepository.findById(dto.getUserId()).get().getNickname());

                replyDtos.add(dto);
            });

        return replyDtos;
    }

    @Override
    public List<ReplyDto> getSubReplies(String replyId, String userId) {
        List<ReplyDto> replyDtos = new ArrayList<>();

        List<ReplyEntity> replies = replyRepository.findByUpReplyIdOrderByCreateDate(replyId);
        replies.forEach((reply)->{
            ReplyDto dto = ReplyDto.toDTO(reply);
            replyDtos.add(dto);
            dto.setNickname(userRepository.findById(dto.getUserId()).get().getNickname());
        });

        return replyDtos;
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
