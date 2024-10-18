package com.movie.test.report.like.service;

import com.movie.test.common.cef.CustomUUID;
import com.movie.test.report.like.dto.LikeDto;
import com.movie.test.report.like.dto.LikeSaveDto;
import com.movie.test.report.like.entity.LikeEntity;
import com.movie.test.report.like.repository.LikeRepository;
import com.movie.test.report.reply.repository.ReplyRepository;
import com.movie.test.report.report.repository.ReportRepository;
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
    private final ReportRepository reportRepository;
    private final ReplyRepository replyRepository;

    @Override
    public LikeDto saveLike(LikeSaveDto likeSaveDto, String userId) {

        // 데이터 유무 체크
        boolean validCheck = false;
        if("report".equals(likeSaveDto.getType())){
            validCheck = reportRepository.existsById(likeSaveDto.getTargetId());
        } else if("reply".equals(likeSaveDto.getType())){
            validCheck = replyRepository.existsById(likeSaveDto.getTargetId());
        }
        // 데이터가 없는 경우 Exception
        if(!validCheck) throw new RuntimeException("존재하지 않는 데이터입니다.");

        LikeDto likeDto = LikeSaveDto.toLikeDto(likeSaveDto);
        likeDto.setLikeId(CustomUUID.createUUID());
        likeDto.setUserId(userId);
        boolean isLikeExist = checkLike(likeDto, userId);
        Optional<UserEntity> userEntity = userRepository.findById(userId);
        if(!isLikeExist && userEntity.isPresent()){
            LikeEntity savedLike = likeRepository.save(LikeDto.toEntity(likeDto));
            return LikeDto.toDTO(savedLike);
        } else if(isLikeExist) {
            // 이미 좋아요한 경우 기존 데이터 리턴
            LikeEntity existLike = likeRepository.findByUserIdAndTargetId(userId, likeSaveDto.getTargetId());
            return LikeDto.toDTO(existLike);
        }

        return likeDto;

    }

    @Override
    public boolean checkLike(LikeDto likeDTO, String userId) {
        return likeRepository.existsByTargetIdAndUserIdAndType(likeDTO.getTargetId(), userId, likeDTO.getType());
    }

    @Override
    public Long countLike(String targetId) {
        return likeRepository.countByTargetId(targetId);
    }

    @Override
    @Transactional()
    public void cancelLike(String likeId, String userId) {
        likeRepository.deleteByLikeIdAndUserId(likeId, userId);
    }

    @Override
    public void deleteLike(String targetId) {
        likeRepository.deleteByTargetId(targetId);
    }
}
