package com.movie.test.hashtag.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

@Builder
@Entity
@ToString
@Getter
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "mv_tagInReport")
public class TagInReportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noMeaningId;

    @Column
    private String reportId;

    @Column
    private Long tagId;
}
