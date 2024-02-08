package com.movie.test.user.token.repository;

import com.movie.test.user.token.entity.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<TokenEntity, String> {

    Optional<TokenEntity> findByRefreshToken(String refreshToken);
}
