package com.movie.test.report.bookmark.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "mv_bookmark")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BookmarkEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookmarkId;

    @Column
    private String userId;

    @Column
    private String reportId;

}
