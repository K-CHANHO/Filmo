package com.movie.test.user.userinfo.service;

import com.amazonaws.services.kms.model.NotFoundException;
import com.movie.test.common.api.s3.service.S3Service;
import com.movie.test.complaint.repository.ComplaintRepository;
import com.movie.test.complaint.service.ComplaintService;
import com.movie.test.inquiry.repository.InquiryRepository;
import com.movie.test.notification.repository.NotificationRepository;
import com.movie.test.report.ReportCompactServiceImpl;
import com.movie.test.report.bookmark.repository.BookmarkRepository;
import com.movie.test.report.bookmark.service.BookmarkService;
import com.movie.test.report.hashtag.repository.TagRepository;
import com.movie.test.report.hashtag.service.TagService;
import com.movie.test.report.like.repository.LikeRepository;
import com.movie.test.report.like.service.LikeService;
import com.movie.test.report.reply.repository.ReplyRepository;
import com.movie.test.report.reply.service.ReplyService;
import com.movie.test.report.report.repository.ReportRepository;
import com.movie.test.report.report.service.ReportService;
import com.movie.test.report.view.repository.ViewRepository;
import com.movie.test.report.view.service.ViewService;
import com.movie.test.user.block.repository.BlockRepository;
import com.movie.test.user.follow.repository.FollowRepository;
import com.movie.test.user.token.dto.JwtTokenDTO;
import com.movie.test.user.token.repository.TokenRepository;
import com.movie.test.user.token.service.JwtTokenProvider;
import com.movie.test.user.userinfo.dto.*;
import com.movie.test.user.userinfo.entity.UserEntity;
import com.movie.test.user.userinfo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final S3Service s3Service;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;

    private final UserRepository userRepository;
    private final ComplaintRepository complaintRepository;
    private final InquiryRepository inquiryRepository;
    private final BlockRepository blockRepository;
    private final FollowRepository followRepository;
    private final TokenRepository tokenRepository;
    private final BookmarkRepository bookmarkRepository;
    private final ReportRepository reportService;
    private final ReplyRepository replyService;
    private final TagRepository tagService;
    private final ComplaintRepository complaintService;
    private final LikeRepository likeService;
    private final ViewRepository viewService;


    @Override
    public UserDto saveUser(UserSignupDto userSignupDto) {

        if(isExistUser(userSignupDto.getEmail())){
            UserEntity existUser = userRepository.findByEmail(userSignupDto.getEmail()).get();
            return UserDto.builder().email(userSignupDto.getEmail()).type(existUser.getType()).build();
        }

        UserDto userDto = UserSignupDto.toUserDto(userSignupDto);

        userDto.setUserId(UUID.randomUUID().toString());
        userDto.setNickname(makeNickname());
        if(userDto.getProfileUrl() != null) {
            userDto.setProfileUrl(s3Service.uploadImage(userDto.getProfileUrl()));
        } else {
            // TODO : 기본 프로필사진 설정하기.
        }
        userDto.setRoles("USER");

        UserEntity user = UserDto.toEntity(userDto);
        UserEntity savedUser = userRepository.save(user);

        return UserDto.toDTO(savedUser);
    }

    public String makeNickname() {

        String[] firstWord = {"영화보는", "친절한", "상냥한", "팝콘먹는", "예매하는", "평화주의"};
        int randomIndex1 = (int)(Math.random() * firstWord.length);

        String[] secondWord = {"케빈", "금자씨", "안톤시거", "트루먼", "코코", "타노스"};
        int randomIndex2 = (int)(Math.random() * secondWord.length);

        String randomNumber = String.valueOf((int)(Math.random() * 10000));

        String nickname = firstWord[randomIndex1] + secondWord[randomIndex2] + randomNumber;

        boolean checkNickname = userRepository.existsByNickname(nickname);
        if (checkNickname) {
            nickname = makeNickname();
        }

        return nickname;
    }

    @Override
    public UserDto getUserInfo(String userid) {

        UserDto userDTO = UserDto.toDTO(userRepository.findById(userid).orElseThrow(()->new NotFoundException("존재하지 않는 사용자입니다.")));

        return userDTO;
    }

    @Override
    public UserDto getUserInfoByUidAndType(String email, String type) {

        UserEntity existUser = userRepository.findByEmailAndType(email, type);

        if(existUser != null) {
            return UserDto.toDTO(existUser);
        }

        return null;
    }

    @Override
    public JwtTokenDTO loginUser(UserDto userDTO) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getType());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 토큰 생성
        JwtTokenDTO jwtTokenDTO = jwtTokenProvider.createToken(userDTO, authentication);

        // 마지막 로그인 시간 저장
        userDTO.setLastLoginDate(new Timestamp(System.currentTimeMillis()));
        userRepository.save(UserDto.toEntity(userDTO));

        return jwtTokenDTO;
    }

    @Override
    public List<String> getUserRoles(String userId) {

        return Arrays.stream(userRepository.findById(userId).orElse(new UserEntity()).getRoles().split(";")).toList();
    }

    @Override
    public UserDto updateUserinfo(UserInfoModifyDto userInfoModifyDto, String loginId) {

        UserEntity userEntity = userRepository.findById(loginId).orElseThrow();
        UserEntity modifiedEntity = userEntity.toBuilder()
                .introduction(userInfoModifyDto.getIntroduction())
                .nickname(userInfoModifyDto.getNickname())
                .profileUrl(userInfoModifyDto.getProfileUrl())
                .build();
        UserEntity saved = userRepository.save(modifiedEntity);

        return UserDto.toDTO(saved);
    }

    @Override
    public Boolean isExistUser(String email) {
        Boolean isExist = userRepository.existsByEmail(email);
        return isExist;
    }

    @Override
    public Boolean isExistUser(String email, String type) {
        Boolean isExist = userRepository.existsByEmailAndType(email, type);
        return isExist;
    }

    @Override
    @Transactional
    public void deleteUser(UserDeleteDto userDeleteDto, CustomUser user) {
        String userId = user.getUserId();

        if(userDeleteDto.getUserId().equals(userId)){
            complaintRepository.deleteByUserId(userId);
            inquiryRepository.deleteByUserId(userId);
            blockRepository.deleteByUserId(userId);
            followRepository.deleteByUserId(userId);
            tokenRepository.deleteByUserId(userId);
            bookmarkRepository.deleteByUserId(userId);

            //reportService.deleteByUserId(userId);
            replyService.deleteByUserId(userId);
            complaintService.deleteByUserId(userId);
            likeService.deleteByUserId(userId);

            userRepository.deleteById(userId);

        } else {
            throw new RuntimeException("잘못된 접근입니다.");
        }
    }

}
