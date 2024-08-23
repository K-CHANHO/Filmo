package com.movie.test.inquiry.dto;

import com.movie.test.common.dto.BaseTimeDTO;
import com.movie.test.inquiry.entity.InquiryEntity;
import com.movie.test.inquiry.mapper.InquiryMapper;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.nio.charset.StandardCharsets;

@Hidden
@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class InquiryDto extends BaseTimeDTO {

    private String inquiryId;
    private String userId;
    private String category; // 문의유형
    private String title; // 문의제목
    private String content; // 문의내용
    private String answerYN; // 답변 여부
    private String userEmail; // 답변받을 이메일주소

    public static InquiryDto toDTO(InquiryEntity inquiryEntity){

        InquiryDto inquiry = InquiryDto.builder()
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

//        return inquiry;
        return InquiryMapper.INSTANCE.toDto(inquiryEntity);
    }

    public static InquiryEntity toEntity(InquiryDto inquiryDTO) {

        InquiryEntity inquiry = InquiryEntity.builder()
                .inquiryId(inquiryDTO.getInquiryId())
                .userId(inquiryDTO.getUserId())
                .userEmail(inquiryDTO.getUserEmail())
                .category(inquiryDTO.getCategory())
                .title(inquiryDTO.getTitle())
                .content(inquiryDTO.getContent().getBytes(StandardCharsets.UTF_8))
                .answerYN(inquiryDTO.getAnswerYN())
                .build();

//        return inquiry;
        return InquiryMapper.INSTANCE.toEntity(inquiryDTO);
    }


}
