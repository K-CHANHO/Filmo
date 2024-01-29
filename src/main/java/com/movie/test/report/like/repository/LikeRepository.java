package com.movie.test.report.like.repository;

import com.movie.test.report.like.entity.LikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Long, LikeEntity>, LikeReporisotyCustrom {
}
