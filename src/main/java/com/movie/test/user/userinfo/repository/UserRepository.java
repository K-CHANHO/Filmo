package com.movie.test.user.userinfo.repository;

import com.movie.test.user.userinfo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    boolean existsByUidAndType(String uid, String type);
    UserEntity findByUidAndType(String uid, String type);
    boolean existsByNickname(String nickname);
    Optional<UserEntity> findByUid(String uid);
}
