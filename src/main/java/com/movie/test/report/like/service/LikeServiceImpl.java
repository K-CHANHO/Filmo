package com.movie.test.report.like.service;

import com.movie.test.report.like.dto.LikeDTO;
import com.movie.test.report.like.entity.LikeEntity;
import com.movie.test.report.like.repository.LikeRepository;
import com.movie.test.user.userinfo.dto.CustomUser;
import com.movie.test.user.userinfo.entity.UserEntity;
import com.movie.test.user.userinfo.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService{

    private final LikeRepository likeRepository;
    private final UserRepository userRepository;

    @Override
    public void registLike(LikeDTO likeDTO, String userId) {
        likeDTO.setUserId(userId);
        boolean isLikeExist = checkLike(likeDTO, userId);
        Optional<UserEntity> userEntity = userRepository.findByUid(userId);
        if(!isLikeExist && userEntity.isPresent()){
            likeRepository.save(LikeDTO.toEntity(likeDTO));
        }
    }

    @Override
    public boolean checkLike(LikeDTO likeDTO, String userId) {
        return likeRepository.existsByTargetIdAndUserIdAndType(likeDTO.getTargetId(), userId, likeDTO.getType());
    }

    @Override
    public Long countLike(String targetId) {
        return likeRepository.countByTargetId(targetId);
    }

    @Override
    @Transactional()
    public void cancelLike(long likeId, String userId) {
        likeRepository.deleteByLikeIdAndUserId(likeId, userId);
    }

    @Override
    public void deleteLike(String targetId) {
        likeRepository.deleteByTargetId(targetId);
    }
}
