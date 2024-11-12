package com.movie.test.user.userinfo.entity;

import com.movie.test.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;

import java.sql.Timestamp;

@Entity
@Table(name = "mv_user", indexes = @Index(name = "idx_nickname", columnList = "nickname", unique = true))
//@Builder(toBuilder = true)
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@SQLDelete(sql = "UPDATE mv_user SET isDeleted = true, deleteDate = now() WHERE userId = ?")
public class UserEntity extends BaseTimeEntity {

    @Id
    @Column
    private String userId; // 자체 uid

    @Column(nullable = false, unique = true)
    private String email; // 소셜 uid

    @Column(nullable = false)
    private String type; // 소셜 type

    @Column(unique = true)
    private String nickname; // 닉네임

    @Column
    private String profileUrl; // 프로필사진 url

    @Column
    private Timestamp lastLoginDate; // 마지막 로그인 시간

    @Column
    private String introduction; // 소개글

    @Column
    private String roles;

}
