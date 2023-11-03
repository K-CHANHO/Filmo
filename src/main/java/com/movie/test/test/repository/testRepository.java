package com.moive.test.test.repository;

import com.moive.test.test.entity.testEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface testRepository extends JpaRepository<testEntity, String> {
}
