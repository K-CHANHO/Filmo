package com.movie.test.report.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "report")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@DynamicUpdate
public class reportEntity {

    @Id
    private String reportId; // 감상문 id

    @Column
    private String title; // 감상문 제목

    @Column
    @Lob
    private byte[] content; // 감상문 내용

    @Column
    private String userId; // 작성자 userId

    @CreationTimestamp
    @Column
    private Timestamp createDate; // 감상문 작성시간

    @UpdateTimestamp
    @Column
    private Timestamp updateDate; // 감상문 수정시간
}
