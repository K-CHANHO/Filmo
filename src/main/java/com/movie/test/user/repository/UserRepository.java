package com.movie.test.user.repository;

import com.movie.test.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    UserEntity findByUidAndType(String uid, String type);

    boolean existsByNickname(String nickname);
}
