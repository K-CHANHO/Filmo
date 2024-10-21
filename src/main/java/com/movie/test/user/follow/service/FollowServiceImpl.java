package com.movie.test.user.follow.service;

import com.movie.test.common.cef.CustomUUID;
import com.movie.test.user.follow.dto.FollowListSearchDTO;
import com.movie.test.user.follow.dto.FollowSaveDto;
import com.movie.test.user.follow.entity.FollowEntity;
import com.movie.test.user.follow.repository.FollowRepository;
import com.movie.test.user.userinfo.dto.UserDto;
import com.movie.test.user.userinfo.entity.UserEntity;
import com.movie.test.user.userinfo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService{

    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    @Override
    public FollowSaveDto saveFollow(FollowSaveDto followSaveDto) {

        // 팔로우 대상이 존재하지 않는 유저일 경우
        userRepository.findById(followSaveDto.getTargetId()).orElseThrow(()->new UsernameNotFoundException("팔로우하려는 유저는 존재하지 않는 유저입니다."));

        // 이미 팔로우한 경우
        FollowEntity checkValid = followRepository.findByUserIdAndTargetId(followSaveDto.getUserId(), followSaveDto.getTargetId());
        if(checkValid != null){
                return FollowSaveDto.toDto(checkValid);
        }
        // 팔로잉 없는 경우 바로 저장
        else {
            followSaveDto.setFollowId(CustomUUID.createUUID());
            FollowEntity savedFollow = followRepository.save(FollowSaveDto.toEntity(followSaveDto));
            return FollowSaveDto.toDto(savedFollow);
        }

    }

    @Override
    public void cancleFollow(String followId, String userId) {

        followRepository.findById(followId)
                .filter(followEntity -> followEntity.getUserId().equals(userId))
                .orElseThrow(()-> new RuntimeException("잘못된 접근입니다."));
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
    public boolean isFollowing(String userId, String targetId) {
        Boolean isFollowing = followRepository.existsByUserIdAndTargetId(userId, targetId);

        return isFollowing;
    }

    @Override
    public String getFollowId(String userId, String targetId) {
        return followRepository.findByUserIdAndTargetId(userId, targetId).getFollowId();
    }

    @Override
    public Long countFollowing(String userId) {
        return followRepository.countByUserId(userId);
    }

    @Override
    public Long countFollower(String userId) {
        return followRepository.countByTargetId(userId);
    }

    @Override
    public Long countBlock(String userId) {
        return followRepository.countByUserId(userId);
    }
}
