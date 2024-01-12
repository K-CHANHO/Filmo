package com.movie.test.follow.service;

import com.movie.test.follow.dto.FollowDTO;
import com.movie.test.follow.entity.FollowEntity;
import com.movie.test.follow.repository.FollowRepository;
import com.movie.test.user.dto.UserDTO;
import com.movie.test.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public Slice<UserDTO> getFollowingUserInfo(String userId, String lastUserId, Pageable pageable) {
        Slice<UserEntity> followingUserInfoEntity = followRepository.getFollowingUserInfo(userId, lastUserId, pageable);
        Slice<UserDTO> followingUserInfoDTO = followingUserInfoEntity.map(UserDTO::toDTO);
        return followingUserInfoDTO;
    }

    @Override
    public Slice<UserDTO> getFollowerUserInfo(String followTarget, String lastUserId, Pageable pageable) {
        Slice<UserEntity> followerUserInfoEntity = followRepository.getFollowerUserInfo(followTarget, lastUserId, pageable);
        Slice<UserDTO> followerUserInfoDTO = followerUserInfoEntity.map(UserDTO::toDTO);
        return followerUserInfoDTO;
    }

    @Override
    public boolean isFollowing(String userId, String followTarget) {
        boolean isFollowing = followRepository.existsByUserIdAndFollowTarget(userId, followTarget);
        return isFollowing;
    }

    @Override
    public Long countFollowing(String userId) {
        return followRepository.countByUserId(userId);
    }

    @Override
    public Long countFollower(String userId) {
        return followRepository.countByFollowTarget(userId);
    }
}
