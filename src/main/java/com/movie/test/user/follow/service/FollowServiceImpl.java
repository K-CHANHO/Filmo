package com.movie.test.user.follow.service;

import com.movie.test.user.follow.dto.FollowDTO;
import com.movie.test.user.follow.dto.FollowListSearchDTO;
import com.movie.test.user.follow.entity.FollowEntity;
import com.movie.test.user.follow.repository.FollowRepository;
import com.movie.test.user.userinfo.dto.UserDto;
import com.movie.test.user.userinfo.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService{

    private final FollowRepository followRepository;

    @Override
    public FollowDTO registFollowing(FollowDTO followDTO) {

        // 이미 팔로우한 경우
        FollowEntity checkValid = followRepository.findByUserIdAndFollowTarget(followDTO.getUserId(), followDTO.getFollowTarget());
        if(checkValid != null){
                return FollowDTO.toDTO(checkValid);
        }

        // 팔로잉 없는 경우 바로 저장
        else {
            StringBuilder stringBuilder = new StringBuilder(String.valueOf(System.currentTimeMillis()));
            stringBuilder.append(UUID.randomUUID().toString());

            followDTO.setFollowId(stringBuilder.toString());
            FollowEntity savedFollow = followRepository.save(FollowDTO.toEntity(followDTO));
            return FollowDTO.toDTO(savedFollow);
        }

    }

    @Override
    public void cancleFollow(String followId) {
        followRepository.deleteById(followId);
    }

    @Override
    public Slice<UserDto> getFollowingUserInfo(FollowListSearchDTO followListSearchDTO, Pageable pageable) {
        Slice<UserEntity> followingUserInfoEntity = followRepository.getFollowingUserInfo(followListSearchDTO, pageable);
        Slice<UserDto> followingUserInfoDTO = followingUserInfoEntity.map(UserDto::toDTO);
        return followingUserInfoDTO;
    }

    @Override
    public Slice<UserDto> getFollowerUserInfo(FollowListSearchDTO followListSearchDTO, Pageable pageable) {
        Slice<UserEntity> followerUserInfoEntity = followRepository.getFollowerUserInfo(followListSearchDTO, pageable);
        Slice<UserDto> followerUserInfoDTO = followerUserInfoEntity.map(UserDto::toDTO);
        return followerUserInfoDTO;
    }

    @Override
    public boolean isFollowing(String userId, String followTarget) {
//        FollowEntity isFollowing = followRepository.findByUserIdAndFollowTarget(userId, followTarget);
        Boolean isFollowing = followRepository.existsByUserIdAndFollowTarget(userId, followTarget);

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

    @Override
    public Long countBlock(String userId) {
        return followRepository.countByUserId(userId);
    }
}
