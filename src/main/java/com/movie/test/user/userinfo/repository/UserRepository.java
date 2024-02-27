package com.movie.test.user.userinfo.repository;

import com.movie.test.user.userinfo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    UserEntity findByUidAndType(String uid, String type);

    boolean existsByNickname(String nickname);

    boolean existsByUid(String uid);

    Optional<UserEntity> findByUid(String uid);
}
