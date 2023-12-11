package com.movie.test.hashtag.repository;

import com.movie.test.hashtag.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<TagEntity, Long> {

    @Query(value = "select t.content" +
            " from tags t" +
            " where t.tagId in :tagIds"
            , nativeQuery = true)
    List<String> findByTagIds(@Param("tagIds") List<Long> tagIds);

    TagEntity findByContent(String content);
}
