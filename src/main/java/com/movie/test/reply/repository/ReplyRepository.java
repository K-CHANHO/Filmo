package com.movie.test.reply.repository;

import com.movie.test.reply.entity.ReplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyRepository extends JpaRepository<ReplyEntity, String> {

    List<ReplyEntity> findByReportIdOrderByCreateDate(String reportId);

    void deleteByReportId(String reportId);

    void deleteByUpReplyId(String upReplyId);

}
