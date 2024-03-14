package com.movie.test.user.block.entity;

import com.movie.test.common.entity.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "mv_block")
@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@DynamicUpdate
public class BlockEntity extends BaseTimeEntity {

    @Id
    private String blockId;

    @Column
    private String userId; // 사용자 id

    @Column
    private String targetId; // 차단 대상

}
