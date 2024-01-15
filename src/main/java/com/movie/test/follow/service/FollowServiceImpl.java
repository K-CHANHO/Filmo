package com.movie.test.follow.service;

import com.movie.test.follow.dto.FollowDTO;
import com.movie.test.follow.dto.FollowListSearchDTO;
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

        FollowDTO returnDTO = null;

        // 이미 팔로우/차단한 경우 type 비교
        FollowEntity checkValid = followRepository.findByUserIdAndFollowTarget(followDTO.getUserId(), followDTO.getFollowTarget());
        if(checkValid != null){
            // type 같을 경우 기존 데이터 리턴
            if(checkValid.getType().equals(followDTO.getType())){
                returnDTO = FollowDTO.toDTO(checkValid);
            }
            // type 다른 경우 update
            else {
                FollowEntity changeTypeEntity = checkValid.toBuilder().type(followDTO.getType()).build();
                FollowEntity savedFollow = followRepository.save(changeTypeEntity);
                returnDTO = FollowDTO.toDTO(savedFollow);
            }
        }
        // 팔로우/차단 없는 경우 바로 저장
        else {
            FollowEntity savedFollow = followRepository.save(FollowDTO.toEntity(followDTO));
            returnDTO = FollowDTO.toDTO(savedFollow);
        }

        return returnDTO;
    }

    @Override
    public void cancleFollow(String followId) {
        followRepository.deleteById(followId);
    }

    @Override
    public Slice<UserDTO> getFollowingUserInfo(FollowListSearchDTO followListSearchDTO, Pageable pageable) {
        Slice<UserEntity> followingUserInfoEntity = followRepository.getFollowingUserInfo(followListSearchDTO, pageable);
        Slice<UserDTO> followingUserInfoDTO = followingUserInfoEntity.map(UserDTO::toDTO);
        return followingUserInfoDTO;
    }

    @Override
    public Slice<UserDTO> getFollowerUserInfo(FollowListSearchDTO followListSearchDTO, Pageable pageable) {
        Slice<UserEntity> followerUserInfoEntity = followRepository.getFollowerUserInfo(followListSearchDTO, pageable);
        Slice<UserDTO> followerUserInfoDTO = followerUserInfoEntity.map(UserDTO::toDTO);
        return followerUserInfoDTO;
    }

    @Override
    public String isFollowing(String userId, String followTarget) {
        FollowEntity isFollowing = followRepository.findByUserIdAndFollowTarget(userId, followTarget);

        String followType = "no-follow";
        if(isFollowing != null){
            followType = isFollowing.getType();
        }

        return followType;
    }

    @Override
    public Long countFollowing(String userId) {
        return followRepository.countByUserIdAndType(userId, "follow");
    }

    @Override
    public Long countFollower(String userId) {
        return followRepository.countByFollowTarget(userId);
    }

    @Override
    public Long countBlock(String userId) {
        return followRepository.countByUserIdAndType(userId, "block");
    }
}
