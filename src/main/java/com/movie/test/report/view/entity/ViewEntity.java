package com.movie.test.report.view.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "mv_report_view")
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class ViewEntity {

    @Id
    private String reportId;

    @Column
    private Long viewCount;
}
