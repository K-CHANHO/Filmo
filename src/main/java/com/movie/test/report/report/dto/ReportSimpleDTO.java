package com.movie.test.report.report.dto;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Hidden
public class ReportSimpleDTO {

    private String reportId; // 감상문 id

    private String title; // 제목
    private String content; // 내용
    private Timestamp createDate; // 작성시간
    private String imageUrl; // 이미지
    private String nickname; // 닉네임
    private Long likeCount; // 좋아요 수
    private Long replyCount; // 댓글 수

    private Long reportCount; // 검색 감상문 수

}
