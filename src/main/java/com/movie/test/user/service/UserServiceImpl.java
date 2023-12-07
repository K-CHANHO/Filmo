package com.movie.test.user.service;

import com.movie.test.s3.service.S3Service;
import com.movie.test.user.dto.UserDTO;
import com.movie.test.user.entity.UserEntity;
import com.movie.test.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private S3Service s3Service;

    @Override
    public UserDTO newUserSave(UserDTO userDTO) {

        userDTO.setUserId(UUID.randomUUID().toString());
        userDTO.setNickname(makeNickname());
        if(userDTO.getProfileURL() != null) {
            userDTO.setProfileURL(s3Service.uploadImage(userDTO.getProfileURL()));
        } else {
            // TODO : 기본 프로필사진 설정하기.
        }

        UserEntity user = UserDTO.toEntity(userDTO);

        UserEntity savedUser = userRepository.save(user);

        return UserDTO.toDTO(savedUser);

    }

    @Override
    public String makeNickname() {

        String[] firstWord = {"영화보는", "노래하는", "춤추는", "달리는", "퇴근하는"};
        int randomIndex1 = (int)(Math.random() * firstWord.length);

        String[] secondWord = {"인어공주", "타노쓰", "디카프리오", "아이언맨", "배트맨"};
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
    public UserDTO getUserInfo(String userid) {
        Map<String, Object> userinfoMap = new HashMap<>();

        UserDTO userDTO = UserDTO.toDTO(userRepository.findById(userid).get());

        return userDTO;
    }
}
