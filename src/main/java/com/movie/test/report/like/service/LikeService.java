package com.movie.test.report.like.service;

import com.movie.test.report.like.dto.LikeDto;
import com.movie.test.report.like.dto.LikeSaveDto;

public interface LikeService {
    LikeDto saveLike(LikeSaveDto likeDTO, String userId);

    boolean checkLike(LikeDto likeDTO, String userId);

    Long countLike(String targetId);

    void cancelLike(String likeId, String userId);

    void deleteLike(String targetId);
}
