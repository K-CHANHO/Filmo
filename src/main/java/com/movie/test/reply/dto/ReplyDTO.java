package com.movie.test.reply.dto;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Hidden
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReplyDTO {

    private String replyId; // 댓글 고유 id
    private String upReplyId; // 대댓글인 경우 원댓글 id
    private String reportId; // 감상문 id
    private String userId; // 댓글 작성자 id
    private String content; // 댓글내용

    private Timestamp createDate; // 댓글작성시간
    private Timestamp lastModifiedDate; // 댓글수정시간
}
