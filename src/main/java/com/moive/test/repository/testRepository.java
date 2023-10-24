package com.moive.test.repository;

import com.moive.test.entity.testEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface testRepository extends JpaRepository<testEntity, String> {
}
