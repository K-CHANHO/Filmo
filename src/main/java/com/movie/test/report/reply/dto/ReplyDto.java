package com.movie.test.report.reply.dto;

import com.movie.test.common.dto.BaseTimeDTO;
import com.movie.test.report.reply.entity.ReplyEntity;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Hidden
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ReplyDto extends BaseTimeDTO {

    private String replyId; // 댓글 고유 id
    private String upReplyId; // 대댓글인 경우 원댓글 id
    private String reportId; // 감상문 id
    private String userId; // 댓글 작성자 id
    private String content; // 댓글내용

    private String nickname; // 댓글 작성자 닉네임
    private List<ReplyDto> subReply;

    public static ReplyDto toDTO(ReplyEntity entity) {
        ReplyDto dto = ReplyDto.builder()
                .replyId(entity.getReplyId())
                .upReplyId(entity.getUpReplyId())
                .reportId(entity.getReportId())
                .userId(entity.getUserId())
                .content(entity.getContent())
                .createDate(entity.getCreateDate())
                .lastModifiedDate(entity.getLastModifiedDate())
                .build();

        return dto;
    }

    public static ReplyEntity toEntity(ReplyDto dto){
        ReplyEntity entity = ReplyEntity.builder()
                .replyId(dto.getReplyId())
                .upReplyId(dto.getUpReplyId())
                .reportId(dto.getReportId())
                .userId(dto.getUserId())
                .content(dto.getContent())
                .build();

        return entity;
    }

}
