package com.moive.test.user.repository;

import com.moive.test.user.entity.userEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userRepository extends JpaRepository<userEntity, String> {

    userEntity findByUidAndType(String uid, String type);
}
