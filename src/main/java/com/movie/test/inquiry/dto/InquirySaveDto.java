package com.movie.test.inquiry.dto;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class InquirySaveDto {

    @Hidden
    private String inquiryId;

    @Hidden
    private String userId; // 사용자 아이디

    @Schema(description = "문의제목")
    private String title; // 문의제목

    @Schema(description = "문의내용")
    private String content; // 문의내용

    @Schema(description = "문의유형")
    private String category; // 문의유형

    @Schema(description = "답변받을 이메일 주소")
    private String userEmail; // 답변받을 이메일주소



}
