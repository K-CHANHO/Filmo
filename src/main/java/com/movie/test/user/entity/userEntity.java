package com.movie.test.user.entity;

import com.movie.test.common.entity.baseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import java.sql.Timestamp;

@Entity
@Table(name = "user")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class userEntity extends baseTimeEntity {

    @Id
    @Column
    private String userId; // 자체 uid

    @Column
    private String uid; // 소셜 uid

    @Column
    private String type; // 소셜 type

    @Column
    private String nickname; // 닉네임

    @Column
    private String profileURL; // 프로필사진 url

    @LastModifiedDate
    @Column
    private Timestamp lastLoginDate; // 마지막 로그인 시간

}
