package com.movie.test.report.like.service;

import com.movie.test.report.like.dto.LikeDTO;

public interface LikeService {
    void regist(LikeDTO likeDTO);
    void delete(LikeDTO likeDTO);

    LikeDTO checkLike(LikeDTO likeDTO);

    Long countLike(String reportId);

    void deleteLike(String reportId);
}
