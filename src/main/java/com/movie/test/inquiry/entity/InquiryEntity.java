package com.movie.test.inquiry.entity;

import com.movie.test.common.entity.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@DynamicInsert
@Table(name = "inquiry")
public class InquiryEntity extends BaseTimeEntity {

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
    private String userEmail; // 답변받을 email 주소

    @Column
    @ColumnDefault("'N'")
    private String answerYN; // 답변 여부


}
