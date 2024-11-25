package com.movie.test.report.like.entity;

import com.movie.test.common.entity.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "mv_like")
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@DynamicUpdate
@SQLDelete(sql = "UPDATE mv_like SET isDeleted = true, deleteDate = now() WHERE likeId = ?")
@Where(clause = "isDeleted is not true")
public class LikeEntity extends BaseTimeEntity {

    @Id
    private String likeId;

    @Column
    private String userId;

    @Column
    private String targetId;

    @Column
    private String type;
}
