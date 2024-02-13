package com.movie.test.user.follow.entity;

import com.movie.test.common.entity.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "mv_follow")
@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@DynamicUpdate
public class FollowEntity extends BaseTimeEntity {

    @Id
    @UuidGenerator
    private String followId;

    @Column
    private String userId; // 팔로우 당사자

    @Column
    private String followTarget; // 팔로우 대상

    @Column
    private String type; // follow(팔로우) 또는 block(차단)
}
