package com.movie.test.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "user")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class userEntity {

    @Id
    @Column
    private String userId; // 자체 uid

    @Column
    private String uid; // 소셜 uid

    @Column
    private String type;

    @Column
    private String nickname;

    @Column
    private String profileURL; // 프로필사진 url

    @CreationTimestamp
    @Column
    private Timestamp createDate;

    @UpdateTimestamp
    @Column
    private Timestamp updateDate;

    @UpdateTimestamp
    @Column
    private Timestamp lastLoginDate;

}
