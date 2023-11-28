package com.movie.test.inquiry.entity;

import com.movie.test.common.entity.baseTimeEntity;
import com.movie.test.inquiry.dto.inquiryDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@Table(name = "inquiry")
public class inquiryEntity extends baseTimeEntity {

    @Id
    @Column
    private String inquiryId; // 문의사항 id

    @Column
    private String userId; // 작성자 id

    @Column
    private String title; // 문의제목

    @Column
    private byte[] content; // 문의내용

    @Column
    private String answerYN; // 답변 여부

    public inquiryEntity toEntity(inquiryDTO inquiryDTO){
        inquiryEntity inquiry = inquiryEntity.builder()
                .inquiryId(inquiryDTO.getInquiryId())
                .userId(inquiryDTO.getUserId())
                .title(inquiryDTO.getTitle())
                .content(inquiryDTO.getContent())
                .answerYN(inquiryDTO.getAnswerYN())
                .build();

        return inquiry;
    }
}
