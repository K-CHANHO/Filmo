package com.movie.test.inquiry.dto;

import com.movie.test.common.dto.BaseTimeDTO;
import com.movie.test.inquiry.entity.InquiryEntity;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.nio.charset.StandardCharsets;

@Hidden
@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InquiryDTO extends BaseTimeDTO {

    private String inquiryId;
    private String userId;
    private String category; // 유형
    private String title; // 문의제목
    private String content; // 문의내용
    private String answerYN; // 답변 여부
    private String userEmail; // 답변받을 이메일주소

    public static InquiryDTO toDTO(InquiryEntity inquiryEntity){

        InquiryDTO inquiry = InquiryDTO.builder()
                .inquiryId(inquiryEntity.getInquiryId())
                .userId(inquiryEntity.getUserId())
                .userEmail(inquiryEntity.getUserEmail())
                .category(inquiryEntity.getCategory())
                .title(inquiryEntity.getTitle())
                .content(new String(inquiryEntity.getContent(), StandardCharsets.UTF_8))
                .answerYN(inquiryEntity.getAnswerYN())
                .createDate(inquiryEntity.getCreateDate())
                .lastModifiedDate(inquiryEntity.getLastModifiedDate())
                .build();

        return inquiry;
    }

    public static InquiryEntity toEntity(InquiryDTO inquiryDTO) {

        InquiryEntity inquiry = InquiryEntity.builder()
                .inquiryId(inquiryDTO.getInquiryId())
                .userId(inquiryDTO.getUserId())
                .userEmail(inquiryDTO.getUserEmail())
                .category(inquiryDTO.getCategory())
                .title(inquiryDTO.getTitle())
                .content(inquiryDTO.getContent().getBytes(StandardCharsets.UTF_8))
                .answerYN(inquiryDTO.getAnswerYN())
                .build();

        return inquiry;
    }


}
