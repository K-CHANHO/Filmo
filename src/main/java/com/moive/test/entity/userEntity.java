package com.moive.test.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

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
    private String userid;

    @Column
    private String type;

    @Column
    private String nickname;
}
