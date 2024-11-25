package com.movie.test.report.bookmark.entity;

import com.movie.test.common.entity.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "mv_bookmark")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@SQLDelete(sql = "UPDATE mv_bookmark SET isDeleted = true, deleteDate = now() WHERE bookmarkId = ?")
@Where(clause = "isDeleted is not true")
public class BookmarkEntity extends BaseTimeEntity {

    @Id
    private String bookmarkId;

    @Column
    private String userId;

    @Column
    private String reportId;

}
