package com.movie.test.follow.service;

import com.movie.test.follow.dto.FollowDTO;
import com.movie.test.follow.entity.FollowEntity;
import com.movie.test.follow.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService{

    private final FollowRepository followRepository;

    @Override
    public FollowDTO registFollowing(FollowDTO followDTO) {

        // 이미 팔로우한 경우 기존 데이터 리턴
        FollowEntity checkValid = followRepository.findByUserIdAndFollowTarget(followDTO.getUserId(), followDTO.getFollowTarget());
        if(checkValid != null){
            return FollowDTO.toDTO(checkValid);
        }

        FollowEntity savedFollow = followRepository.save(FollowDTO.toEntity(followDTO));

        return FollowDTO.toDTO(savedFollow);
    }

    @Override
    public void cancleFollow(String followId) {
        followRepository.deleteById(followId);
    }
}
