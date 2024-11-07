package com.movie.test.report.bookmark.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;

@Entity
@Table(name = "mv_bookmark")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@SQLDelete(sql = "UPDATE mv_bookmark SET isDeleted = true, deleteDate = now() WHERE bookmarkId = ?")
public class BookmarkEntity {

    @Id
    private String bookmarkId;

    @Column
    private String userId;

    @Column
    private String reportId;

}
