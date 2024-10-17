package com.movie.test.report.like.service;

import com.movie.test.report.like.dto.LikeDTO;
import com.movie.test.user.userinfo.dto.CustomUser;

public interface LikeService {
    void registLike(LikeDTO likeDTO, String userId);

    boolean checkLike(LikeDTO likeDTO, String userId);

    Long countLike(String targetId);

    void cancelLike(long likeId, String userId);

    void deleteLike(String targetId);
}
