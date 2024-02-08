package com.movie.test.user.token.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;

@Entity
@Table(name = "mv_user_token")
@Builder
@Getter
public class TokenEntity {

    @Id
    private String userId;

    @Column
    private String refreshToken;

}
