package com.movie.test.reply.repository;

import com.movie.test.reply.entity.replyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface replyRepository extends JpaRepository<replyEntity, String> {

    List<replyEntity> findByReportIdOrderByCreateDate(String reportId);

    void deleteByReportId(String reportId);

    void deleteByUpReplyId(String upReplyId);

}
