package com.movie.test.report.hashtag.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Builder
@Entity
@ToString
@Getter
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "mv_tagInReport")
@SQLDelete(sql = "UPDATE mv_tagInReport SET isDeleted = true, deleteDate = now() WHERE reportId = ?")
@Where(clause = "isDeleted is not true")
public class TagInReportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noMeaningId;

    @Column
    private String reportId;

    @Column
    private Long tagId;
}
