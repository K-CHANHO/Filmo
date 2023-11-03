package com.moive.test.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.Date;

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
    private String userid; // 자체 uid

    @Column
    private String uid;

    @Column
    private String type;

    @Column
    private String nickname;

    @Column
    private String profileURL; // 프로필사진 url

    @CreationTimestamp
    @Column
    private Timestamp create_date;

    @UpdateTimestamp
    @Column
    private Timestamp update_date;

    @Column
    private Timestamp last_login_date;

}
