package com.movie.test.user.block.entity;

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
@Table(name = "mv_block")
@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@DynamicUpdate
@SQLDelete(sql = "UPDATE mv_block SET isDeleted = true, deleteDate = now() WHERE blockId = ?")
@Where(clause = "isDeleted is null || isDeleted = 0")
public class BlockEntity extends BaseTimeEntity {

    @Id
    private String blockId;

    @Column
    private String userId; // 사용자 id

    @Column
    private String targetId; // 차단 대상

}
