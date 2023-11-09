package com.movie.test.user.repository;

import com.movie.test.user.entity.userEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userRepository extends JpaRepository<userEntity, String> {

    userEntity findByUidAndType(String uid, String type);

    boolean existsByNickname(String nickname);
}
