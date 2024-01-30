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
    public void registOrDelete(LikeDTO likeDTO) {

        LikeDTO isLikeExist = checkLike(likeDTO);
        if(isLikeExist == null){
            likeRepository.save(LikeDTO.toEntity(likeDTO));
        } else {
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
    public Long countLike(LikeDTO likeDTO) {
        Long count = likeRepository.countByReportId(likeDTO.getReportId());
        return count;
    }
}
