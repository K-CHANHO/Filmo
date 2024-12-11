package com.movie.test.report.reply.repository;

import com.movie.test.report.reply.entity.ReplyEntity;

import java.util.HashMap;
import java.util.List;

public interface ReplyRepositoryCustom {

    List<ReplyEntity> findFirstDepthReply(String reportId, String userId);
    List<ReplyEntity> findSubReplies(HashMap map);

}
