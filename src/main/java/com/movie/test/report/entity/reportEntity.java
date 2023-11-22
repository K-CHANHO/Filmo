package com.movie.test.report.entity;

import com.movie.test.common.entity.baseTimeEntity;
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
public class reportEntity extends baseTimeEntity {

    @Id
    private String reportId; // 감상문 id

    @Column
    private String title; // 감상문 제목

    @Column
    @Lob
    private byte[] content; // 감상문 내용

    @Column
    private String userId; // 작성자 userId

}
