package com.movie.test.report.reply.repository;

import com.movie.test.report.reply.entity.ReplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyRepository extends JpaRepository<ReplyEntity, String> {

    List<ReplyEntity> findByReportIdOrderByCreateDate(String reportId);
    List<ReplyEntity> findByReportIdAndUpReplyIdIsNullOrderByCreateDate(String reportId);
    List<ReplyEntity> findByUpReplyIdOrderByCreateDate(String replyId);

    void deleteByReportId(String reportId);

    void deleteByUpReplyId(String upReplyId);
}
