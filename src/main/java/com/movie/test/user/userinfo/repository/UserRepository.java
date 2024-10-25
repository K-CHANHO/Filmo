package com.movie.test.user.userinfo.repository;

import com.movie.test.user.userinfo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    Boolean existsByEmail(String email);
    Boolean existsByEmailAndType(String email, String type);
    UserEntity findByEmailAndType(String email, String type);
    boolean existsByNickname(String nickname);
    Optional<UserEntity> findByEmail(String email);
}
