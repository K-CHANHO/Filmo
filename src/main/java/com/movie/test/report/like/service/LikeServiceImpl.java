package com.movie.test.report.like.service;

import com.movie.test.report.like.dto.LikeDTO;
import com.movie.test.report.like.entity.LikeEntity;
import com.movie.test.report.like.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService{

    private final LikeRepository likeRepository;

    @Override
    public void regist(LikeDTO likeDTO) {

        LikeDTO isLikeExist = checkLike(likeDTO);
        if(isLikeExist == null){
            likeRepository.save(LikeDTO.toEntity(likeDTO));
        }

    }

    @Override
    public void delete(LikeDTO likeDTO) {

        LikeDTO isLikeExist = checkLike(likeDTO);
        if(isLikeExist != null){
            likeRepository.deleteById(isLikeExist.getLikeId());
        }
    }

    @Override
    public LikeDTO checkLike(LikeDTO likeDTO) {
        LikeEntity isLikeExist = likeRepository.findByReportIdAndUserId(likeDTO.getReportId(), likeDTO.getUserId());
        if(isLikeExist != null){
            return LikeDTO.toDTO(isLikeExist);
        }

        return null;
    }

    @Override
    public Long countLike(String reportId) {
        Long count = likeRepository.countByReportId(reportId);
        return count;
    }

    @Override
    public void deleteLike(String reportId) {
        likeRepository.deleteByReportId(reportId);
    }
}
