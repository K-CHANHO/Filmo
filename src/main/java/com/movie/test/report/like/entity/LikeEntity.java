package com.movie.test.report.like.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "mv_report_like")
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@DynamicUpdate
public class LikeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeId;

    @Column
    private String reportId;

    @Column
    private String userId;
}
