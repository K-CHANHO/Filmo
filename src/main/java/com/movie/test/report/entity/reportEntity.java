package com.movie.test.report.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "report")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class reportEntity {

    @Id
    private String reportId; // 감상문 id

    @Column
    private String title; // 감상문 제목

    @Column
    @Lob
    private byte[] content; // 감상문 내용

    @Column
    private String userid; // 작성자 userid

    @CreationTimestamp
    @Column
    private Timestamp register_date; // 감상문 작성시간

    @UpdateTimestamp
    @Column
    private Timestamp update_date; // 감상문 수정시간
}
